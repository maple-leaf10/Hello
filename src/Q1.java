import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Q1{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String MealName1,MealName2,FrcName1,FrcName2,BeerName,JuiceName;
        double price1,price2,BCost,JCost,balance;
        int Bday,Jday;
        BeerName=scanner.nextLine();
        BCost= scanner.nextDouble();
        Bday= scanner.nextInt();//啤酒参数
        Beer b1=new Beer(BeerName,BCost,Bday);
        b1.count = 0;
        b1.degree = scanner.nextFloat();
        scanner.nextLine();
        JuiceName=scanner.nextLine();
        JCost= scanner.nextDouble();
        Jday= scanner.nextInt();//果汁参数
        Beer[] b={b1};
        System.out.println(b1.toString());
        Juice j1=new Juice(JuiceName,JCost,Jday);
        j1.count=0;
        System.out.println(j1.toString());
        Juice[] j={j1};
        MealName1=scanner.nextLine();
        scanner.nextLine();
        FrcName1=scanner.nextLine();
        price1= scanner.nextDouble();
        scanner.nextLine();
        MealName2=scanner.nextLine();
        scanner.nextLine();
        FrcName2=scanner.nextLine();
        price2= scanner.nextDouble();
        SetMeal s1=new SetMeal(MealName1,price1,FrcName1,b1);//套餐一
        SetMeal s2=new SetMeal(MealName2,price2,FrcName2,j1);//套餐二
        SetMeal[] s={s1,s2};
        balance= scanner.nextDouble();//店余额
        West2FriedChickenRestauran WFC =new West2FriedChickenRestauran(balance,b,j);
        West2FriedChickenRestauran.getSetMeal(s);
        WFC.BulkPurchase(b1,123,100);
        if(b1.Overdue(String.valueOf(2020-12-6))){
            System.out.println(b1.name+"is overdue");
        }
        WFC.SellMeal(b1,5);
        FriedChickenRestaurant f = null;
        assert false;
        f.SellMeal(b1,5);
        f.BulkPurchase(b1,123,5);
        IngredientSortOutException i=null;
        assert false;
        i.Sort(b1);
        OverdraftBalanceException o=null;
        assert false;
        o.PurchaseCost=0;
        o.Buy(1000,20);
        WFC.addBeer(b1);
        WFC.addJuice(j1);
        WFC.remove(b1);
    }
}
abstract class Drinks{
    protected String name;//名字
    protected double cost;//成本
    protected int shelf_day;//保质期
    protected int count;//数量
    protected String LocalDate(){
        Scanner scanner=new Scanner(System.in);
        return scanner.nextLine();//生产日期
    }
    public Drinks(String name,double cost,int shelf_day){
        this.name = name;
        this.cost = cost;
        this.shelf_day = shelf_day;
    }
    boolean Overdue(String current){//判断过期
        String date=LocalDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// 自定义时间格式
        Calendar calendar_a = Calendar.getInstance();// 获取日历对象
        Calendar calendar_b = Calendar.getInstance();
        Date date_a = null;
        Date date_b = null;
        try {
            date_a = simpleDateFormat.parse(date);//字符串转Date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date_b = simpleDateFormat.parse(current);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date_a != null;
        calendar_a.setTime(date_a);// 设置日历
        assert date_b != null;
        calendar_b.setTime(date_b);
        int time_a = (int) calendar_a.getTimeInMillis();
        int time_b = (int) calendar_b.getTimeInMillis();
        int days = (time_b - time_a) / (1000 * 3600 * 24);//计算相差天数
        return days > shelf_day;
    }
    @Override
    public String toString() {
        return "Drinks{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", shelf_day=" + shelf_day +
                '}';
    }
}
class Beer extends Drinks{
    public Beer(String name, double cost, int shelf_day) {
        super(name, cost,shelf_day);
        this.shelf_day = 30;//保质期为30天
    }
    public float degree;//酒精度数
}
class Juice extends Drinks{
    public Juice(String name, double cost, int shelf_day) {
        super(name, cost, shelf_day);
        this.shelf_day = 2;//保质期为2天
    }
}
class SetMeal{
    private final String MealName;//套餐名
    private final double price;//套餐价格
    private final String FCName;//炸鸡名
    private final Drinks drinks;//饮料类成员
    SetMeal(String mealName, double price, String fcName, Drinks drinks) {
        MealName = mealName;
        this.price = price;
        FCName = fcName;
        this.drinks = drinks;
    }
    @Override
    public String toString() {
        return "SetMeal{" +
                "MealName='" + MealName + '\'' +
                ", price=" + price +
                ", FCName='" + FCName + '\'' +
                ", drinks=" + drinks +
                '}';
    }
}
interface FriedChickenRestaurant{
    void SellMeal(Drinks d,int count);//购买数量
    void BulkPurchase(Drinks d,double money,int count);//所需钱数、购买数量
}
class IngredientSortOutException extends RuntimeException{
    public void Sort(Drinks d){
        try {
                if(d.count<=0)//库存不足
                    throw new NumberFormatException("null");
        } catch (NumberFormatException e) {
            System.out.println(d.name+" is sold out!");
        }
    }
}
class OverdraftBalanceException extends RuntimeException{
    public double PurchaseCost;
    public OverdraftBalanceException(double purchaseCost) {
        PurchaseCost=purchaseCost;
    }
    public void Buy(double PurchaseCost, double balance){
        try{
                if(PurchaseCost>balance)//余额不足
                    throw new OverdraftBalanceException(PurchaseCost);
        }catch (OverdraftBalanceException e){
            double temp=PurchaseCost-balance;
            System.out.println("还差"+temp+"元");
        }
    }
}
class West2FriedChickenRestauran implements FriedChickenRestaurant{
    private double balance;//余额
    private final List<Beer> BList = new ArrayList<>();//啤酒列表，不进行固定位置插入，用数组实现更方便
    private final List<Juice> JList = new ArrayList<>();//果汁列表
    private static final List<SetMeal> MList = new ArrayList<>();//套餐列表
    West2FriedChickenRestauran(double balance, Beer[] b, Juice[] j) {
        this.balance = balance;
        BList.addAll(Arrays.asList(b));
        JList.addAll(Arrays.asList(j));
    }
    void addBeer(Beer b){
        BList.add(b);
    }
    void remove(Beer b){
        BList.remove(b);
    }
    void addJuice(Juice j){
        JList.add(j);
    }
    public static void getSetMeal(SetMeal... m) {
        MList.addAll(Arrays.asList(m));
    }
    static{
        getSetMeal();
    }
    private void use(Beer b){
        Scanner scanner=new Scanner(System.in);
        for (Beer s : BList) {//判断是否有过期啤酒并移除
            String curr = scanner.nextLine();
            if (s.Overdue(curr)) {
                BList.remove(s);
            }
        }
        if(BList.contains(b)){
            System.out.println(b.name+"is exist");
            BList.remove(b);
        }
        else throw new NumberFormatException("null");//库存不足，抛出异常
    }
    private void use(Juice j){
        Scanner scanner=new Scanner(System.in);
        for (Juice s : JList) {//判断是否有过期饮料并移除
            String curr = scanner.nextLine();
            if (s.Overdue(curr)) {
                JList.remove(s);
            }
        }
        if(JList.contains(j)){
            System.out.println(j.name+"is exist");
            JList.remove(j);
        }
        else throw new NumberFormatException("null");//库存不足，抛出异常
    }
    static {
        System.out.println(MList.size());
    }
    @Override
    public void SellMeal(Drinks d,int count) {//批量出售
        if (d instanceof Beer) {
            if (BList.contains(d) && d.count >= count) {
                System.out.println("成功售出啤酒" + count + "瓶");
                d.count-=count;
                balance+=count*d.cost;
            }
            else{
                System.out.println("库存不足");
                Beer b=(Beer)d;
                use(b);
            }
        }
        else if(d instanceof Juice){
            if(JList.contains(d)&&d.count>=count){
                System.out.println("成功售出果汁" + count + "瓶");
                d.count-=count;
                balance+=count*d.cost;
            }
            else{
                System.out.println("库存不足");
                Juice j=(Juice)d;
                use(j);
            }
        }
    }
    @Override
    public void BulkPurchase(Drinks d, double money,int count){//批量进货
        if(d instanceof Beer){
            if(money<=balance){
                System.out.println("成功进货啤酒"+count+"瓶");
                d.count+=count;
                balance-=money;
            }
            else{
                System.out.println("余额不足");
                throw new OverdraftBalanceException(money);
            }
        }
        else if(d instanceof Juice){
            if(money<=balance){
                System.out.println("成功进货果汁"+count+"瓶");
                d.count+=count;
                balance-=money;
            }
            else{
                System.out.println("余额不足");
                throw new OverdraftBalanceException(money);
            }
        }
    }
}