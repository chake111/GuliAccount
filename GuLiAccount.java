import java.math.BigDecimal;

/**
 * @author chake
*/
// 阶段一的项目：谷粒记账软件的实现
public class GuLiAccount {
    public static void main(String[] args) {
        boolean isFlag = true;
        while (isFlag) {
            MyTools.printBanner("谷粒记账软件", "-");
            MyTools.options();
            MyTools.printBanner("请选择(1-3):", " ");
            switch (Utility.readMenuSelection()) {
                case '1':
                    MyTools.printBanner("当前收支明细记录", "-");
                    // 连接数据库查询明细
                    dbTools.selectDetail();
                    break;
                case '2':
                    // 登记收入或支出的记录
                    String incomeOrExpenditure = MyTools.inputEnum();
                    BigDecimal amount = Utility.readAmount();
                    String comment = MyTools.readComment();
                    dbTools.insertDetail(incomeOrExpenditure, amount, comment);
                    break;
                case '3':
                    char isExit = Utility.readConfirmSelection();
                    if (isExit == 'Y') {
                        isFlag = false;
                    }
                    System.out.println("已退出");
                    break;
            }
        }
    }
}
