import java.util.Scanner;

/**
 * @author chake
 */
public class MyTools {

    public static void printBanner(String content, String div) {
     String dividingLine = div.repeat(20);
     System.out.println(dividingLine + content + dividingLine);
 }
    public static void options() {
        // 菜单
        String spaces = " ".repeat(20);
        String options =spaces + "1 收入明细\n" +
                spaces + "2 登记收入\n" +
                spaces + "3 退   出\n";
        System.out.println(options);
    }
    /*
    读取字符串
    校验合法性（限定为‘支出’ 或 ‘收入’）
    */
    public static String inputEnum () {
        System.out.println("收支:");
        Scanner sc = new Scanner(System.in);
        String incomeOrExpenditure = "";
        boolean isTrue = false;
        while (!isTrue) {
            incomeOrExpenditure = sc.nextLine();
            switch (incomeOrExpenditure) {
                case "支出", "收入": isTrue = true; break;
                default:
                    System.out.println("输入正确类型（只能选“收入”/“支出”）:");
            }
        }
        return incomeOrExpenditure;
    }
    public static String readComment () {
        System.out.println("说明(可选):");
        Scanner sc = new Scanner(System.in);
        return "%s".formatted(sc.nextLine());
    }
}
