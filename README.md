# Recipe Lab（汉化分支）

<p align="center">
  <img src="dist/icon-512.png" width="96" alt="Recipe Lab 图标">
</p>

<h1 align="center">Recipe Lab（配方实验室）</h1>

<p align="center">
  为 <b>Sony A6000</b> 制作的胶片模拟与相机色彩配方，直接写入相机内部。
  <br>
  <sub>
    <img src="https://img.shields.io/github/v/release/iKeilo/recipe-lab-sony-pmca-cn?label=version" alt="version"> ·
    <a href="https://github.com/iKeilo/recipe-lab-sony-pmca-cn/releases/latest/download/RecipeLab-cn.apk">下载汉化版 APP</a> ·
    <a href="docs/SAMPLES.md">查看全部配方</a>
  </sub>
</p>

---

## 中文汉化更新记录（本分支）

> 本分支 = **RecipeLab 的汉化分支，仅提供汉化界面**。
> 相机逻辑、设置读写、配方数据与主线（voxivoid/recipe-lab-sony-pmca 1.4.1）完全一致。

### 版本 v1.4.1-cn（当前 Release）

- 界面全量汉化：应用菜单、开发者菜单、关于页、确认弹窗、按键图例、提示语、错误报告、画质/快照/样张全流程文案。
- 内置简体中文字体：部分机型（如 ILCE-7SM2）系统回退字体链未启用简体字库，导致「览 / 验 / 单」等简体字显示为方框；
  本分支将固件自带字体 MYingHeiC-GB18030-SJ.ttf 打包进 APK，所有界面统一使用该字体渲染。
- 产物：RecipeLab-cn.apk（约 1.19 MB，含字体），版本号对齐主线 1.4.1。
- 校验：SHA-256: 13f1e6cdbfe76c5782279c6fbb5b8d43dc43c8f3cbd107bbf6490519c84dde86
- 下载：https://github.com/iKeilo/recipe-lab-sony-pmca-cn/releases/latest

### 本地迭代记录（vNdsset 系列）

| 版本 | 内容 |
|---|---|
| v3 ～ v16dsset | 早期实验版本（写后读回校验 / 日志导出 / A7S II 预览通道深挖等），不构成此分支 |
| v17dsset | 基于主线干净源码，仅做汉化第一版（资源骨架 + 主界面） |
| v18dsset | 补齐菜单/开发者面板/提示全量汉化 |
| v19dsset | 内置固件简体字体 + 图例/面板字体接入 |
| v21dsset | 修复字体注入时机（自定义视图 setFont），全部 Canvas 文本正常渲染 |
| **v1.4.1-cn** | 定稿发布（见上） |

### 与主线的差异清单（唯一差异 = 汉化）

- 新增：res/values-zh-rCN/strings.xml、src/.../UiText.java、docs/LOCALIZATION.md、assets/fonts/MYingHeiC-GB18030-SJ.ttf
- 修改：仅界面文案相关源文件（MainActivity / DevTools / Keys / Params / Favourites / MenuView / PickerView / PromptView / HintBar / Legend / res/values/strings.xml）
- 明确未改：相机通信、设置槽位读写、预览参数路径、配方数据与编码、samples/diff 文件格式。

### 使用注意（与汉化无关，机型通用）

- 模式拨盘在 AUTO（智能自动）时相机会自行接管创意风格，配方不生效：请使用 P / A / S / M。
- 有 Picture Profile 菜单的机型（如 A7S II）请保持 Picture Profile = Off，否则创意风格系参数会被相机忽略。

---

**目录**

- [这是什么](#这是什么)
- [配方清单](#配方清单)
- [示例样张](docs/SAMPLES.md)
- [机型兼容性](#机型兼容性)
- [安装](#安装)
- [使用方法](#使用方法)
- [它会改动什么](#它会改动什么)
- [卸载](#卸载)
- [故障排查](#故障排查)
- [常见问题](docs/FAQ.md)
- [开发者](#开发者)
- [致谢](#致谢)

---

## 这是什么

Recipe Lab 是一个直接运行在 Sony A6000 相机上的小程序，内置 76 款色彩配方，重现其他机型的观感——
富士胶片模拟、理光 GR 影像控制、徕卡、哈苏、佳能与尼康的色彩、索尼新一代 Creative Looks，
以及 Kodak、Fuji、Cinestill、Agfa、Ilford 的经典胶片风格。

转动拨轮，实时画面随之变化，按下按键——从此相机在**所有模式**（照片与视频）下都按这个观感拍摄，
关闭 App 依然有效。关机再开机，它依旧在。

> **坦率说明。** A6000 没有 Picture Profile 菜单，也无法存储色调曲线。每一款配方都只由这台相机
> *能保存*的参数构成：创意风格、饱和度、对比度、锐度、白平衡、曝光补偿、照片效果，以及一个
> 索尼从未公开的隐藏色彩设置。因此这些都是对某种观感的近似，而非对其他品牌色彩科学的复制。

## 配方清单

| 系列 | 配方 |
|---|---|
| **Sony** | PT、NT、VV、VV2、FL、IN、SH —— 以及出厂观感（见 **重置设置**） |
| **富士模拟** | Provia、Velvia、Astia、Classic Chrome、Classic Negative、Nostalgic Neg、Reala Ace、Pro Neg Std / Hi、Eterna、Eterna Bleach Bypass、Acros、Acros +Ye / +R / +G、Sepia |
| **富士胶片** | Pro 400H、Fortia 50、Superia 400、C200、Natura 1600 |
| **Kodak** | Portra 160 / 400 / 800、Gold 200、Ultra Max 400、Color Plus 200、Ektar 100、Ektachrome E100、Kodachrome 64、Vision3 500T、Vision 200T (Asteroid City)、Tri-X 400、Tri-X 1600（迫冲）、T-Max |
| **Cine** | Cinestill 50D、Cinestill 800T、Classic Cinema、Rec709 Video |
| **理光 GR** | Positive Film、Negative Film、Bleach Bypass、Retro、Cross Process、Hi-Contrast B&W、Hard Monotone、Soft Monotone |
| **徕卡** | Contemporary、Classic、Eternal、Monochrom |
| **哈苏** | HNCS Natural |
| **佳能 / 尼康** | Canon Standard / Portrait / Faithful、Nikon Flat / Vivid |
| **松下 / 奥林巴斯** | L.Monochrome D、L.ClassicNeo、Pop Art、Pale & Light |
| **其他胶片** | Agfa Vista 200、Agfa Ultra 100、Polaroid / Instax |
| **Ilford** | HP5、FP4、Delta 100、Delta 3200、Pan F 50 |

**[查看全部配方同场景样张 →](docs/SAMPLES.md)** —— 77 张，同一场景、同一曝光，全部直出。

App 内标注 **PE** 的配方（Acros +R、Tri-X 1600、GR Retro、GR Hi-Contrast B&W、Sony SH、Polaroid）
采用照片效果实现——经对比样张，其色调曲线比创意风格更接近目标；其余配方均有意使用创意风格。

因相机能力不足而未收录：对数曲线（S-Log、V-Log、Blackmagic Film、Cinelike D）与染色黑白（硒调、蓝晒）。
索尼摄像机的 Cinematone 伽马虽存在于固件中，但 A6000 的相机层既不列出也不接受，此路亦不通。
## 机型兼容性

Recipe Lab 不含机型检查，所有运行 PlayMemories 应用的索尼机身共享同一套设置存储——
因此理论上可以安装到 A6000 之外并工作。

风险在于：设置 ID 是在 A6000 上找的，在其他机身上可能位于别处，配方可能落到错误位置。
只有当有人在该机型上稳定保存配方并断电重启成功后，才会被标记为 ✅。

试过了？请提交[兼容性报告](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/new?template=compatibility_report.yml)。
失败与成功同样有价值。

### 可运行 PlayMemories 应用的机型

✅ 已有人在真机上运行 · ❔ 可装 App，暂无人反馈

| 相机 | 机型代码 | 状态 | 备注 |
|---|---|---|---|
| **A6000** | ILCE-6000 | ✅ | 本 App 的开发与测试机，固件 3.21 |
| **A6500** | ILCE-6500 | ✅ | 可安装、可保存、断电重启后保留 |
| **A5100** | ILCE-5100 | ✅ | 可用，拨轮可滚动全部配方。该机无 **Fn** 键：品牌列表入口为 **长按 MENU → 浏览配方**；**TRASH**（**? / 删除** 键）隐藏面板——尚未在该机确认（[#18](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/18)） |
| **A7 II** | ILCE-7M2 | ✅ | 报告可用 |
| **A7** | ILCE-7 | ✅ | 两份报告，均为固件 3.20——可保存、断电重启后保留。第一位曾用 OpenMemories-Tweak 解锁设置库；第二位无需解锁（[#51](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/51)） |
| **NEX-5T** | NEX-5T | ✅ | 最老的 App 世代；固件 1.1 可安装、保存并断电保留（App 1.0 报告）——但保存值与菜单显示不完全一致，仍未解决（[#22](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/22)） |
| **A6300** | ILCE-6300 | ✅ | 可保存、断电重启后保留，固件 2.01 |
| **A7R** | ILCE-7R | ✅ | 可保存、断电重启后保留，固件 3.2 |
| **A7R II** | ILCE-7RM2 | ✅ | 三份报告，固件 4.00 与 4.01——可保存、断电重启后保留（[#46](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/46)） |
| **RX100 V** | DSC-RX100M5 | ✅ | 可保存、断电重启后保留，固件 2.00 |
| **HX60 / HX60V** | DSC-HX60 | ✅ | 可保存、断电重启后保留且菜单吻合，固件 2.10，报告于 App 1.1.0——变焦与闪光不可用（[#42](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/42)） |
| **A7S II** | ILCE-7SM2 | ✅ | 固件 3.01 的两份报告相互矛盾：一份在非官方汉化 1.3.1 上保存并断电保留（[#52](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/52)）；另一份得到 App 的保存确认，但关闭 App 后观感丢失，且手动镜头下防抖异常（[#48](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/48)）。两份都称 **AEL** 键无效。该机观感丢失时，请检查 Picture Profile 是否为 Off（见[故障排查](#故障排查)） |
| **RX100 III** | DSC-RX100M3 | ✅ | 可保存、断电重启后保留，固件 2.0（[#47](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/47)） |
| **RX1R II** | DSC-RX1RM2 | ✅ | 可保存、断电重启后保留，固件 1.0（[#53](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/53)） |
| A5000 | ILCE-5000 | ❔ | 与 A5100 相同，预计无 **Fn** 键——品牌列表入口为 **长按 MENU**，**TRASH** 隐藏面板 |
| A7S | ILCE-7S | ❔ |  |
| NEX-5R | NEX-5R | ❔ | 最老的 App 世代 |
| NEX-6 | NEX-6 | ❔ | 菜单与 A6000 世代差异较大，设置位置最可能不同 |
| A68 | ILCA-68 | ❔ | A 卡口；不在安装器机型表中，连安装都未测试 |
| A77 II | ILCA-77M2 | ❔ | A 卡口 |
| A99 II | ILCA-99M2 | ❔ | A 卡口 |
| RX100 IV | DSC-RX100M4 | ❔ |  |
| RX10 II | DSC-RX10M2 | ❔ |  |
| RX10 III | DSC-RX10M3 | ❔ |  |
| HX90 / HX90V | DSC-HX90 | ❔ | 卡片机；缺少 App 依赖的控制拨轮 |
| HX400 / HX400V | DSC-HX400 | ❔ | 卡片机；缺少 App 依赖的控制拨轮 |
| WX500 | DSC-WX500 | ❔ | 卡片机；缺少 App 依赖的控制拨轮 |

另有两点不明：**RX100 II** 与初代 **RX10** 曾有索尼 App 商店，但不在安装器的机型表中，连安装都未测试。

可装但无意义：QX 镜头相机（ILCE-QX1、DSC-QX10/QX30/QX100）无屏幕/拨轮可驱动 App；
Handycam 与运动相机没有可写入的创意风格。

### 无法运行相机 App 的机型

索尼最后一代支持 App 的机身即上表所列（2016 年末的 A6500 与 A99 II）。此后的机型固件签名锁定，
且没有 MENU → 应用程序，一切都装不上：本 App 不行，索尼自己已在 2021 年关闭的商店也不行。

| | |
|---|---|
| **E 卡口 APS-C** | A6100、A6400、A6600、A6700、ZV-E10、ZV-E10 II、FX30 |
| **E 卡口全画幅** | A7 III、A7R III、A7R IV / IVA、A7R V、A7S III、A7C、A7C II、A7CR、A9、A9 II、A9 III、A1、A1 II、ZV-E1、FX3 |
| **Cyber-shot** | RX100 VA、RX100 VI、RX100 VII、RX10 IV、RX0、RX0 II、HX99、ZV-1、ZV-1F、ZV-1 II |

……以及此后发布的所有机型。判断标准看菜单：相机上没有 MENU → 应用程序 就没有 App——
声称 Recipe Lab 在 A6400 等机型上运行的报告均为误传。
## 安装

一次性约十分钟。需要：相机、USB 线、存储卡与电脑（Windows、Mac 或 Linux）。

**1. 获取安装工具。** 名为 Sony-PMCA-RE，由 ma1co 制作。它像索尼自家已关闭的 App 商店一样把应用装入索尼相机。

- *Windows：* 从[发布页](https://github.com/ma1co/Sony-PMCA-RE/releases)下载 pmca-gui.exe。无需安装，直接运行。
- *macOS：* 同一页面有 macOS 版本，测试少于 Windows 版。先关闭占用 USB 设备的程序——Photos、Dropbox、Google Drive——否则它们会抢先占用相机。
- *Linux，或二进制异常时：* Python 3 与 libusb，然后在终端运行：

    git clone https://github.com/ma1co/Sony-PMCA-RE.git
    cd Sony-PMCA-RE
    pip install -r requirements.txt

**2. 下载本应用：**
[RecipeLab-cn.apk](https://github.com/iKeilo/recipe-lab-sony-pmca-cn/releases/latest/download/RecipeLab-cn.apk)
——该链接始终指向最新汉化版。
[发布页](https://github.com/iKeilo/recipe-lab-sony-pmca-cn/releases) 含历史版本与校验文件。

**3. 准备相机。** 电池充满，卡已插入。相机菜单进入 设置（工具箱图标）→ USB 连接 并选择 **Mass Storage**。打开相机并连上电脑。相机屏幕应显示 *USB Mode*。

**4. 安装。**

- *GUI：* 打开 pmca-gui.exe → **Install app from file** → 选择 RecipeLab-cn.apk → 等待。
- *终端，* 在 Sony-PMCA-RE 目录下（Linux 前加 sudo）：

    python pmca-console.py install -f RecipeLab-cn.apk

相机会闪屏、黑屏并自行切换几次模式，属正常——不要按任何键。约一分钟后电脑显示 Task completed successfully。**以电脑提示为准。**
相机通常停留在自己的 Application Download / Connecting via USB... 界面，看起来卡住，实际没有。

**5. 拔线，然后关机再开机。** 应用现在位于 MENU → 应用程序 → 应用程序列表 → Recipe Lab。

## 使用方法

从应用程序列表打开 **Recipe Lab**。你会看到实时画面与底部信息面板，然后：

| 键 | 功能 |
|---|---|
| **拨轮** | 任意界面滚动配方——实时画面立即变化，也正是相机将要写入的观感 |
| **左 / 右**、**顶拨盘** | 同样滚动配方，但仅在配方行；在芯片行则移动芯片 |
| **中心键** | 应用当前查看的配方——相机保存它。有消息确认 |
| **长按中心键** | 标记/取消收藏配方。主界面与品牌列表内均可用 |
| **上 / 下** | 在配方行与参数芯片行之间切换 |
| **TRASH** | 隐藏面板——按一次显示小标签，两次全隐，三次恢复。拨轮始终有效 |
| **长按 TRASH** | 恢复出厂观感——App 先询问确认 |
| **长按 MENU** | 打开菜单：浏览配方、面板显示、重置设置、关于 |
| **快门** | 拍摄当前预览画面 |
| **MENU** | 退出 App |
| **Fn** *（若你的相机有）* | 打开品牌列表——同 **长按 MENU → 浏览配方** |

所有功能都使用每台相机都有的按键；**Fn** 是拥有该键机身的快捷键，仅当相机报告存在时才在屏幕底部图例中显示。**AEL**、**C1** 与 **DISP** 在 App 中无功能。

**菜单。** 长按 **MENU** 片刻，菜单全屏显示；短按仍为退出 App。上 / 下或拨轮移动，中心键选择，**MENU** 返回。

- **浏览配方** 打开品牌列表：左侧 **收藏** 优先，其后是各品牌；右侧是配方。左 / 右切换列（当前列为琥珀色），拨轮或上 / 下滚动，中心键选择。
- **面板显示** —— 完整、标签 或 隐藏 —— 左 / 右直接切换。
- **重置设置** 在询问确认后把相机恢复为出厂观感。出厂观感不在配方列表中，此为其入口。
- **关于** 显示应用版本、相机型号、平台版本与源码地址。
- **开发者** 提供测试与兼容性报告所需工具。

随后 **关机再开机**。此观感现在是相机在所有模式（P、A、S、M、视频）下的默认样式，即使 App 关闭也有效，且 App 下次打开停留在该配方。

**收藏。** 在配方上**长按中心键**即加入品牌列表顶部的 **收藏** 组，名称旁出现星标；再次长按移出。组内按收藏顺序排列，且当当前配方属于收藏时，品牌列表会直接打开在收藏组。主界面拨轮仍遍历全部 76 款——收藏只是缩短浏览器中的列表，不影响滚动。收藏标记由 App 保存（不在相机设置中），因此断电重启不消失，但卸载 App 会一并清除。

**参数芯片。** 在芯片行中，**左 / 右** 移动芯片，**中心键** 聚焦（变琥珀色），**上 / 下** 改值，**中心键** 结束编辑。此间拨轮仍可切换配方。配方只显示用到的芯片：**CS** 配方显示风格、饱和度、对比度与锐度；**PE** 配方显示效果及其子项。画质、白平衡、EV 与 DRO 始终在列。屏幕底部的图例随当前操作变化。

**徽标** 说明当前状态：

| 徽标 | 含义 |
|---|---|
| **生效** | 相机已保存这些值 |
| **预览** | 你只是在预览；按 **中心键** 应用 |

## 它会改动什么

只改你本可手动设置的相机参数：创意风格及其饱和度/对比度/锐度滑块、白平衡及其微调、曝光补偿、DRO、照片效果。不触碰固件，不解锁任何东西。

**照片效果配方**（标注 **PE**）与菜单项行为一致：效果开启时相机忽略创意风格，且仅当 **画质 = JPEG** 才生效——设为 RAW 或 RAW+JPEG 时相机会静默丢弃效果。

因此**画质**跟随你而非由配方决定。出厂配方以相机当前设置为起点，所有创意风格配方沿用。随时可用 画质 芯片修改。仅当配方需要 JPEG 而你处于 RAW 时 App 会询问：

    画质: RAW+JPG → JPG 精细 — 应用此配方需要 JPEG

*取消* 则不做任何改动。

## 卸载

**是永久性的吗？** 观感会保留到你主动更改——这是刻意设计，使它在无 App 时也能作用于所有模式。它不是破坏意义上的永久。随时可用三种方式撤销：

- App 内：**长按 TRASH**（或 **长按 MENU → 重置设置**），确认后关机再开机。
- 菜单内：把创意风格改回 标准 0 / 0 / 0，白平衡改回 自动。
- 或使用相机自带的 设置 → 设置重置 → 相机设置复位。

**移除应用。** MENU → 应用程序 → 应用程序管理 → 管理和删除 → Recipe Lab。这**不会**恢复色彩设置，因此请先撤销观感再删除应用。

**须知：**

- App 内的预览是临时的，关闭 App 即消失。只有你**应用过**的会保留。
- 基于固件 3.21 的 A6000 开发与测试。多款机型已有成功报告——见[机型兼容性](#机型兼容性)——但表中仍为 ❔ 的机型，应用配方前请先对照芯片显示与相机菜单。
## 故障排查

常见问题——RAW 文件、损坏、LUT、新机型——见 **[常见问题](docs/FAQ.md)**。

| 你看到的 | 该做的 |
|---|---|
| No devices found | USB 连接须为 Mass Storage；卡已插入；相机开机并显示 USB Mode；换线或换口 |
| 卡在 Waiting for camera to switch... | 拔线，关开机，重连，重新运行 |
| 未写入 — 相机将这些设置设为只读，或 写入失败 | 相机拒绝了配方。安装 [OpenMemories-Tweak](https://github.com/ma1co/OpenMemories-Tweak)，打开 **Protection**，勾选 Unlock protected settings 直至显示 Protection disabled，然后重新应用配方 |
| 应用配方后观感未生效 | 关机再开机 |
| App 内观感正常，退出即消失 | 若你的相机有 **Picture Profile** 菜单（A6000 没有），把 MENU → Picture Profile 设为 Off 后重新应用配方。Picture Profile 运行时相机会忽略创意风格及其滑块。旧版使用色彩矩阵的配方可能在部分机型上自行开启 PP3（[#38](https://github.com/voxivoid/recipe-lab-sony-pmca/issues/38)）；当前版本应用任何配方都会将其关闭 |
| 面板显示 无实时预览: ... | 有其他程序占用相机；关闭并重开 App |
| 文本出现 Â· | 旧版本；从[最新发布](https://github.com/iKeilo/recipe-lab-sony-pmca-cn/releases/latest)安装 APK |
| 部分汉字显示为方框 | 安装本分支最新汉化版（已内置简体字体）；主线原版未含中文字体 |

## 开发者

逆向工程笔记——源码布局、设置存储 ID 对照表、退出规则、实时预览参数、按键扫描码与构建方法——见 **[docs/DEVELOPMENT.md](docs/DEVELOPMENT.md)**。

参与贡献前请先阅读 **[docs/CONTRIBUTING.md](docs/CONTRIBUTING.md)**：分支命名、提交格式与发布流程均由 CI 强制约束。

汉化方案与迁移规则见 **[docs/LOCALIZATION.md](docs/LOCALIZATION.md)**。

## 致谢

**作者：** [André Domingues (voxivoid)](https://github.com/voxivoid) —— A6000 设置存储逆向（备份 ID、PP 标志行为、色彩矩阵测算）、应用、配方与图标。

**特别感谢 [ma1co](https://github.com/ma1co)。** 没有他多年对索尼 PlayMemories 相机平台的逆向，这一切都不存在：

- [Sony-PMCA-RE](https://github.com/ma1co/Sony-PMCA-RE) —— 应用安装通道、用于导出本相机固件与设置的更新器壳，以及 fwtool。
- [OpenMemories-Platform](https://github.com/ma1co/OpenMemories-Platform) —— 本应用链接的备份驱动 / OSAL 绑定（以 git submodule 内嵌）。
- [OpenMemories-Tweak](https://github.com/ma1co/OpenMemories-Tweak) 与 [OpenMemories-Framework](https://github.com/ma1co/OpenMemories-Framework) —— Backup_read/write、ScalarInput 键码与 CameraEx API 的参考。
- 他承继并持续记录的早期 nex-hack 社区研究。

他弄清了这些相机的工作原理，公开记录并以宽松许可发布——本项目只是站在其上。

**同时感谢 [Veres Deni Alex](https://www.veresdenialex.com/)。** 他的索尼胶片模拟配方与并排对照样张是许多观感的灵感与基准（Kodak、Fuji、Cinestill、Ilford、Cinema…）。本应用中的数值均针对 A6000 可保存能力重新推导，并非他的配方。

**汉化分支维护者：** [iKeilo](https://github.com/iKeilo) —— 中文本地化、内置字体与发布。

许可证：MIT（本仓库）。OpenMemories-Platform：MIT，© 2017 ma1co。
