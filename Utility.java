import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 通用输入工具类
 * 封装控制台输入验证，包含以下功能：
 * 1.菜单选项验证  2.数字格式验证
 * 3.字符串验证    4.确认操作验证
 * @author chake
 */
public class Utility {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * 读取菜单选项（仅接受1-4的字符输入）
     * @return 有效的菜单选项字符
     */
    public static char readMenuSelection() {
        char c;
        while (true) {
            String str = readKeyBoard(1);
            c = str.charAt(0);
            if (c >= '1' && c <= '3') {
                break;
            } else {
                System.out.print("输入错误，请重新输入(1-3)：");
            }
        }
        return c;
    }

    /**
     * 读取4位以内整数
     * @return 有效整数
     */
    public static int readNumber() {
        int n;
        while (true) {
            String str = readKeyBoard(4);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("请输入有效数字(最多4位)：");
            }
        }
        return n;
    }

    /**
     * 读取8位以内字符串
     * @return 输入字符串
     */
    public static String readString() {
        return readKeyBoard(100);
    }

    /**
     * 确认选择（Y/N）
     * @return 大写确认字符
     */
    public static char readConfirmSelection() {
        System.out.print("确认是否退出(Y/N)：");
        char c;
        while (true) {
            String str = readKeyBoard(1).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("请输入Y/N确认：");
            }
        }
        return c;
    }

    /**
     * 通用输入方法（带长度限制）
     * @param limit 最大输入长度
     * @return 有效输入字符串
     */
    private static String readKeyBoard(int limit) {
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            if (line.length() <= limit) {
                break;
            }
            System.out.printf("输入长度不能超过%d个字符，请重新输入：", limit);
        }
        return line;
    }
    /**
     * 读取金额输入（格式：最多10位整数，2位小数）
     * @return 有效的BigDecimal金额
     */

    public static BigDecimal readAmount() {
        System.out.println("金额:");
        while (true) {
            // 允许输入长度（如：1234567890.99）
            String input = readKeyBoard(13);
            try {
                // 校验格式：必须为数字，且小数点后最多2位
                if (!input.matches("^\\d+(\\.\\d{1,2})?$")) {
                    throw new IllegalArgumentException();
                }
                return new BigDecimal(input);
            } catch (Exception e) {
                System.out.print("金额格式错误（示例：5000.99），请重新输入：");
            }
        }
    }
    // 安全关闭Scanner
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (scanner != null) {
                scanner.close();
            }
        }));
    }
}