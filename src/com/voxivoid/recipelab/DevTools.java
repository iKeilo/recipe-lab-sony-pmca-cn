package com.voxivoid.recipelab;

/**
 * The app menu (MENU hold) and the developer menu under it, and the sample run it can start: the rows each menu
 * has, the About and key-logger pages, the settle delay the run waits between applying a recipe and firing the
 * shutter, the progress lines it shows, and the manifest it writes so the frames can be matched to recipes afterwards.
 *
 * The run itself is a timed loop in MainActivity (stage a recipe → wait → shutter → wait → next); everything it
 * decides without the camera is here. No android.* import may appear in this class (tools/test.sh).
 */
final class DevTools {
    private DevTools() {}

    static final String APP_TITLE = "配方实验室", TITLE = "开发者工具", ABOUT_TITLE = "关于";

    /** the two menu levels: the app menu a MENU hold opens, and the developer menu under it */
    static final int LEVEL_APP = 0, LEVEL_DEV = 1;

    /** app menu rows, in display order */
    static final int APP_BROWSE = 0, APP_PANEL = 1, APP_RESET = 2, APP_ABOUT = 3, APP_DEV = 4, APP_ROWS = 5;

    /** developer menu rows, in display order */
    static final int ROW_SNAPSHOT = 0, ROW_LOCKS = 1, ROW_SAMPLES = 2, ROW_SETTLE = 3, ROW_KEYS = 4, ROWS = 5;

    /**
     * Settle delays to pick from, in ms: how long the preview pipeline gets after a recipe is applied before the
     * shutter fires. The right one is a property of the camera, not of this table — a frame that still carries the
     * previous look means the delay is too short, which is why the row exists instead of a constant.
     */
    static final int[] SETTLE_MS = { 800, 1200, 2000, 3000, 5000 };
    /** the delay a fresh install starts on */
    static final int SETTLE_DEFAULT = 1;
    /** what the run gives a capture before it stages the next recipe, in ms (the shutter key's press → release, automated) */
    static final int SHUTTER_MS = 2500;

    /** the frame list the run writes into the app's files dir; the frames themselves are named by the camera */
    static final String MANIFEST = "samples.txt";
    /** field separator of a manifest line; no recipe name contains it (RecipesTest) */
    static final String SEP = "|";

    // ------------------------------------------------------------ the app menu
    /** how many rows a level has */
    static int rows(int level) { return level == LEVEL_APP ? APP_ROWS : ROWS; }

    /** the row above / below on a level, wrapping */
    static int nextRow(int level, int row, int dir) { int n = rows(level); return (row + n + dir) % n; }

    /** an app menu row's title */
    static String appLabel(int row) {
        switch (row) {
            case APP_BROWSE: return "浏览配方";
            case APP_PANEL: return "面板显示";
            case APP_RESET: return "重置设置";
            case APP_ABOUT: return "关于";
            case APP_DEV: return "开发者  >";
            default: return "?" + row;
        }
    }

    /** the line under an app menu row's title */
    static String appDetail(int row) {
        switch (row) {
            case APP_BROWSE: return "品牌与收藏";
            case APP_PANEL: return "实时画面上的信息层 — 左 / 右切换";
            case APP_RESET: return "恢复相机出厂观感";
            case APP_ABOUT: return "版本、相机、平台";
            case APP_DEV: return "设置快照、只读检查、样张、按键记录";
            default: return "";
        }
    }

    /**
     * The value an app menu row shows at its right edge, which left / right change in place; null for a row that has
     * none. Panel visibility shows the panel state: Full, Label (the pill) or Hidden.
     */
    static String appValue(int row, int overlay) { return row == APP_PANEL ? panelLabel(overlay) : null; }

    /** a panel state as the menu names it */
    static String panelLabel(int overlay) {
        switch (overlay) {
            case Params.OV_FULL: return "完整";
            case Params.OV_PILL: return "标签";
            case Params.OV_HIDDEN: return "隐藏";
            default: return "?";
        }
    }

    /** the panel state left / right lands on: full → label → hidden, wrapping; the browser is never one of them */
    static int nextPanel(int overlay, int dir) {
        int o = overlay >= Params.OV_FULL && overlay <= Params.OV_HIDDEN ? overlay : Params.OV_FULL;
        return (o + 3 + dir) % 3;
    }

    /** the About page: {name, value}. The version comes from the installed package at runtime, never from here. */
    static String[][] about(String version, String model, String platform) {
        return new String[][] {
            { "版本", orUnknown(version) },
            { "相机", orUnknown(model) },
            { "平台", orUnknown(platform) },
            { "源码", "github.com/voxivoid/recipe-lab-sony-pmca" },
        };
    }

    // ------------------------------------------------------------ the reset question (hold trash, or Reset settings)
    /** the question asked before the factory look is stored: it replaces whatever the camera has now */
    static final String RESET_TITLE = "重置为出厂设置？",
            RESET_BODY = "写入 标准 0 / 0 / 0、自动白平衡、无效果，取代当前观感";
    /** the answers; Cancel is the one highlighted when the question opens, so a stray centre press changes nothing */
    static final String[] RESET_OPTIONS = { "重置", "取消" };
    static final int RESET_DEFAULT = 1;

    /**
     * What the key probe found, as one line: "has Fn AEL C1  ·  lacks DISP  ·  unknown ZOOM_T". {@code has} lines up
     * with {@code scans}; a null entry is a key the camera would not answer for.
     */
    static String keysFound(int[] scans, Boolean[] has) {
        StringBuilder yes = new StringBuilder(), no = new StringBuilder(), unk = new StringBuilder();
        for (int i = 0; i < scans.length; i++) {
            StringBuilder b = has[i] == null ? unk : has[i] ? yes : no;
            b.append(b.length() == 0 ? "" : " ").append(Keys.name(scans[i]));
        }
        if (yes.length() == 0 && no.length() == 0) return "相机未报告";
        StringBuilder out = new StringBuilder();
        if (yes.length() > 0) out.append("有 ").append(yes);
        if (no.length() > 0) out.append(out.length() == 0 ? "" : "  ·  ").append("无 ").append(no);
        if (unk.length() > 0) out.append(out.length() == 0 ? "" : "  ·  ").append("未知 ").append(unk);
        return out.toString();
    }

    private static String orUnknown(String s) { return s == null || s.isEmpty() ? "未知" : s; }

    // ------------------------------------------------------------ the key logger
    /** how many events the logger keeps on screen, newest first */
    static final int LOG_LINES = 10;
    /** the file the logger appends to in the app's files dir */
    static final String KEY_LOG = "keys.txt";

    /** the logger page title: the body it runs on */
    static String logTitle(String model, String platform) { return "按键记录  ·  " + orUnknown(model) + "  ·  " + orUnknown(platform); }

    /**
     * One key event: {"down 595", "DELETE  ·  repeat 0  ·  logic 1103"}. The scan code is what a compatibility report
     * needs; the name, the repeat count and Sony's logic code (null before platform API 3) are what make sense of it.
     */
    static String[] logLine(boolean down, int scan, int repeat, Integer logic) {
        String name = Keys.name(scan);
        return new String[] { (down ? "按下 " : "抬起 ") + scan,
                (name.isEmpty() ? "?" : name) + "  ·  重复 " + repeat + (logic == null ? "" : "  ·  逻辑 " + logic) };
    }

    // ------------------------------------------------------------ the developer menu
    /** the row above / below, wrapping */
    static int nextRow(int row, int dir) { return nextRow(LEVEL_DEV, row, dir); }

    /** a row's title; the snapshot row and the delay row say what they will do next */
    static String rowLabel(int row, boolean snapshotTaken, int settle) {
        switch (row) {
            case ROW_SNAPSHOT: return snapshotTaken ? "设置对比" : "设置快照";
            case ROW_LOCKS: return "只读检查 — " + Params.allSlots().size() + " 个槽位";
            case ROW_SAMPLES: return "拍摄样张 — " + Recipes.ALL.length + " 个配方";
            case ROW_SETTLE: return "稳定延时";
            case ROW_KEYS: return "按键记录";
            default: return "?" + row;
        }
    }

    /** the line under a row's title */
    static String rowDetail(int row, boolean snapshotTaken) {
        switch (row) {
            case ROW_SNAPSHOT: return snapshotTaken ? "将每个设置 ID 与快照对比" : "记录每个设置 ID 的当前值";
            case ROW_LOCKS: return "检查配方会写入的每个槽位是否带只读标志";
            case ROW_SAMPLES: return "每个配方一张 JPEG，按表顺序 — MENU 停止";
            case ROW_SETTLE: return "应用配方后、快门触发前的等待时间 — 左 / 右修改";
            case ROW_KEYS: return "显示每个按键的扫描码 — 长按 MENU 退出";
            default: return "";
        }
    }

    /** the value a developer menu row shows at its right edge, which left / right change in place; null for none */
    static String rowValue(int row, int settle) { return row == ROW_SETTLE ? settleLabel(settle) : null; }

    // ------------------------------------------------------------ the settle delay
    /** a stored delay index brought back into the table */
    static int clampSettle(int idx) { return idx >= 0 && idx < SETTLE_MS.length ? idx : SETTLE_DEFAULT; }

    /** the next / previous delay, wrapping */
    static int nextSettle(int idx, int dir) { return (clampSettle(idx) + SETTLE_MS.length + dir) % SETTLE_MS.length; }

    /** a delay as the menu shows it: "1.2 s" (built by hand — String.format would follow the camera's locale) */
    static String settleLabel(int idx) {
        int ms = SETTLE_MS[clampSettle(idx)];
        return (ms / 1000) + "." + (ms % 1000) / 100 + " 秒";
    }

    // ------------------------------------------------------------ the sample run
    /** the run needs the live camera: without it nothing is applied and nothing can be shot */
    static final String NO_PREVIEW = "无实时预览 — 拍摄样张需要相机";

    /** the sticky line while the run walks the table; frames count from 1 */
    static String progress(int frame, int total, String recipeName) {
        return "拍摄中 " + frame + " / " + total + "  ·  " + recipeName + "   —   MENU 停止";
    }

    /** the run reached the end of the table */
    static String doneMessage(int shot, int total) {
        return "样张完成 — 已拍 " + shot + " / " + total + " 帧，清单见 " + MANIFEST;
    }

    /** MENU during the run */
    static String stoppedMessage(int shot, int total) {
        return shot == 0 ? "样张运行在第一帧前停止"
                : "样张运行已停止 — 已拍 " + shot + " / " + total + " 帧，清单见 " + MANIFEST;
    }

    /** the camera refused a capture: the run cannot go on, and the frames so far are still listed */
    static String shootFailed(int frame, int shot, String error) {
        return "第 " + frame + " 帧快门失败: " + error + "  —  已拍 " + shot + " 帧，清单见 " + MANIFEST;
    }

    // ------------------------------------------------------------ the manifest
    /**
     * The first line of a run: what the columns are, and the delay it was shot with. Appended to, so a file can
     * hold several runs and each one says how it was made.
     */
    static String manifestHeader(int total, int settleMs) {
        return "# recipe-lab samples  ·  " + total + " frames in recipe order  ·  settle " + settleMs + " ms"
                + "  ·  frame" + SEP + "recipe" + SEP + "brand" + SEP + "values";
    }

    /** one frame: its number in the run, and the recipe that was applied for it */
    static String manifestLine(int frame, int recipeIndex) {
        Recipes.Recipe r = Recipes.ALL[recipeIndex];
        return pad2(frame) + SEP + r.name + SEP + Recipes.GROUPS[r.group] + SEP + r.summary();
    }

    private static String pad2(int n) { return n < 10 ? "0" + n : String.valueOf(n); }
}
