package com.voxivoid.recipelab;

/** Screen-only translations. Canonical recipe names, keys, and codec text stay in Recipes/Params. */
final class UiText {
    private UiText() {}
    static String group(String value) {
        if ("Sony".equals(value)) return "索尼";
        if ("Fuji Sim".equals(value)) return "富士模拟";
        if ("Fuji Film".equals(value)) return "富士胶片";
        if ("Kodak".equals(value)) return "柯达";
        if ("Cine".equals(value)) return "电影";
        if ("Ricoh GR".equals(value)) return "理光 GR";
        if ("Leica".equals(value)) return "徕卡";
        if ("Hasselblad".equals(value)) return "哈苏";
        if ("Canon / Nikon".equals(value)) return "佳能 / 尼康";
        if ("Pana / Olympus".equals(value)) return "松下 / 奥林巴斯";
        if ("Other Stocks".equals(value)) return "其他胶片";
        if ("Ilford".equals(value)) return "伊尔福";
        if ("Favourites".equals(value)) return "收藏";
        return value;
    }
    static String value(int row, int value, int[] edit) {
        String s = Params.fmt(row, value, edit);
        if (row == Params.R_STYLE || row == Params.R_PE) return summary(s);
        if (row == Params.R_SUB) return sub(s);
        if (row == Params.R_WBMODE) return "auto".equals(s) ? "自动" : "kelvin".equals(s) ? "色温" : s;
        if (row == Params.R_DRO) return "auto".equals(s) ? "自动" : "off".equals(s) ? "关闭" : s;
        if (row == Params.R_QUAL) return s.replace("JPG Fine", "JPG 精细").replace("JPG Std", "JPG 标准");
        return s;
    }
    static String summary(String value) {
        String s = value;
        String[] from = { "Standard", "Vivid", "Neutral", "Portrait", "Landscape", "B&W", "Clear", "Deep", "Light", "Sunset", "Night", "Autumn", "Sepia", "High-key", "Part col", "HC mono", "Soft foc", "HDR art", "Rich mono", "Miniature", "Watercol", "Toy", "Pop", "Poster", "Retro", "PP3", "MTX", "DRO ", "auto" };
        String[] to = { "标准", "鲜艳", "中性", "人像", "风景", "黑白", "清晰", "深色", "明亮", "夕阳", "夜景", "秋叶", "棕褐", "高调", "局部色彩", "高对比单色", "柔焦", "HDR艺术", "浓郁单色", "微缩", "水彩", "玩具相机", "流行色彩", "海报", "复古", "PP3", "矩阵", "DRO ", "自动" };
        for (int i = 0; i < from.length; i++) s = s.replace(from[i], to[i]);
        return s;
    }
    static String meta(String value) {
        return summary(value).replace("Picture Effect", "图片效果").replace("Creative Style ignored, JPEG only", "忽略创意风格，仅JPEG").replace("WB ", "白平衡 ").replace("mode ", "模式 ").replace("QUALITY", "画质").replace("(now ", "（当前 ").replace(")", "）").replace("RAW is on: effect ignored", "已启用RAW：效果已忽略").replace("no live preview: ", "实时预览失败：");
    }
    static String mini(String value) { return summary(value).replace("preview", "预览").replace("active", "已应用").replace("quality →", "画质 →"); }
    private static String sub(String s) { return summary(s).replace("posterization-color", "彩色").replace("posterization-bw", "黑白").replace("normal", "标准").replace("cool", "冷色").replace("warm", "暖色").replace("green", "绿色").replace("magenta", "洋红").replace("blue", "蓝色").replace("pink", "粉色").replace("red", "红色").replace("yellow", "黄色"); }
}
