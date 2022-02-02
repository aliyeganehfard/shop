import Exceptions.DigitException;
import Exceptions.MoneyException;
import Exceptions.NationalCodeException;
import Exceptions.WordException;
import controller.*;
import model.entity.*;
import model.repository.AdminRepository;
import model.repository.CategoryRepository;
import model.repository.CustomerRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var adminService = new AdminService();
        var customerService = new CustomerService();
        var categoryService = new CategoryService();
        var productService = new ProductService();
        var shoppingCardService = new ShoppingCardService();
        var orderService = new OrderService();

        boolean flag = true;
        boolean state = false;

        Customer customer = null;
        Admin admin = null;

        int type = 0;
        String commendLine;
        String commend[];

        while (flag) {
            showMenu();
            System.out.print("commend : ");
            commendLine = scn.nextLine();
            commend = commendLine.trim().split(" ");
            switch (commend[0]) {
                case "registerAdmin":
                    try {
                        ExceptionHandling.isNationalCode(commend[3]);
                        adminService.save(new Admin(commend[1], commend[2], commend[3]));
                    }catch (NationalCodeException nationalCodeException){
                        System.out.println("incorrect nationalCode!");
                    }
                    catch (Exception e){
                        System.out.println("wrong input!");
                    }
                    break;
                case "registerCustomer":
                    try {
                        customerService.save(new Customer(commend[1], commend[2], commend[3]));
                    } catch (Exception e) {
                        System.out.println("wrong input!");
                    }
                    break;
                case "loginAdmin":
                    try {
                        admin = adminService.login(commend[1], commend[2]);
                        if (admin == null) {
                            System.out.println("not found!");
                            break;
                        }
                        showAdminMenu();
                        state = true;
                        type = 1;
                    } catch (Exception e) {
                        System.out.println("wrong input!");
                    }
                    break;
                case "loginCustomer":
                    try {
                        customer = customerService.login(commend[1], commend[2]);
                        if (customer == null) {
                            System.out.println("not found!");
                            break;
                        }
                        showCustomerMenu();
                        state = true;
                        type = 2;
                    } catch (Exception e) {
                        System.out.println("worng input!");
                    }
                    break;
                default:
                    System.out.println("wrong input!");
                    break;
            }

            while (state) {
                switch (type) {
                    case 1:
                        System.out.print("commend : ");
                        commendLine = scn.nextLine();
                        commend = commendLine.trim().split(" ");
                        switch (commend[0]) {
                            case "showProduct":
                                List<Product> productList = productService.findAll();
                                for (Product product : productList) {
                                    System.out.println(product);
                                }
                                break;
                            case "showCategory":
                                List<Category> categoryList = categoryService.findAll();
                                for (Category category : categoryList) {
                                    System.out.println(category);
                                }
                                break;
                            case "addCategory":
                                try {
                                    ExceptionHandling.isWord(commend[1]);
                                    ExceptionHandling.isDigit(commend[3]);
                                    categoryService.save(
                                            new Category(
                                                    commend[1],
                                                    commend[2],
                                                    new Category(Integer.valueOf(commend[3]))
                                            )
                                    );
                                }catch (WordException wordException){
                                    System.out.println("incorrect title!");
                                }catch (DigitException digitException){
                                    System.out.println("incorrect id");
                                }
                                catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "addProduct":
                                try {
                                    ExceptionHandling.isWord(commend[1]);
                                    ExceptionHandling.isDigit(commend[3]);
                                    ExceptionHandling.isDigit(commend[4]);
                                    ExceptionHandling.isMoney(commend[5]);
                                    productService.save(
                                            new Product(
                                                    commend[1],
                                                    commend[2],
                                                    categoryService.findById(Integer.valueOf(commend[3])),
                                                    Integer.valueOf(commend[4]),
                                                    Integer.valueOf(commend[5])
                                            )
                                    );
                                }catch (WordException ee){
                                    System.out.println("incorrect name!");
                                }catch (DigitException dd){
                                    System.out.println("enter digit!");
                                }catch (MoneyException moneyException){
                                    System.out.println("incorrect price!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "update":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ExceptionHandling.isDigit(commend[2]);
                                    ExceptionHandling.isMoney(commend[3]);
                                    productService.update(
                                            new Product(
                                                    Integer.valueOf(commend[1]),
                                                    Integer.valueOf(commend[2]),
                                                    Integer.valueOf(commend[3])
                                            )
                                    );
                                }catch (DigitException dd){
                                    System.out.println("enter digit!");
                                }catch (MoneyException moneyException){
                                    System.out.println("incorrect price!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "help":
                                showAdminMenu();
                                break;
                            case "logout":
                                state = false;
                                break;
                            case "exit":
                                state = false;
                                flag = false;
                                break;
                            default:
                                System.out.println("wong input!");
                                break;
                        }
                        break;
                    case 2:
                        System.out.print("commend : ");
                        commendLine = scn.nextLine();
                        commend = commendLine.trim().split(" ");
                        switch (commend[0]) {
                            case "showProduct":
                                List<Product> productList = productService.findAll();
                                for (Product product : productList) {
                                    System.out.println(product);
                                }
                                break;
                            case "showCategory":
                                List<Category> categoryList = categoryService.findAll();
                                for (Category category : categoryList) {
                                    System.out.println(category);
                                }
                                break;
                            case "showProductByCategoryId":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    productService.findByCategoryId(Integer.valueOf(commend[1]));
                                } catch (DigitException dd){
                                    System.out.println("enter digit!");
                                }catch (Exception e) {
                                    System.out.println("wrong number!");
                                }
                                break;
                            case "showShoppingCard":
                                List<Order> orderList = customerService.findShoppingCardByUserId(customer.getId());
                                for (Order sc :orderList) {
                                    System.out.println(sc.toString());
                                }
                                break;
                            case "addProductToCard":
                                try {
                                    ExceptionHandling.isDigit(commend[1]);
                                    ShoppingCard shoppingCard = null;
                                    if (commend[2].equals("new")){
                                        shoppingCardService.save(
                                                new ShoppingCard(
                                                        Date.valueOf(LocalDate.now()),
                                                        false
                                                )
                                        );
                                        int a = shoppingCardService.findAll().size();
                                        shoppingCard = shoppingCardService.findAll().get(--a);
                                    }else {
                                       shoppingCard = shoppingCardService.findById(Integer.valueOf(commend[2]));
                                    }

                                    Product product = productService.findById(Integer.valueOf(commend[1]));
                                    if (!(product.getQty()>= Integer.parseInt(commend[3]))){
                                        System.out.println("not enough qty!");
                                        break;
                                    }
                                    product.setQty(Integer.valueOf(commend[3]));
                                        orderService.save(
                                                new Order(
                                                        customer,
                                                        product,
                                                        shoppingCard
                                                )
                                        );

                                }catch (DigitException dd){
                                    System.out.println("enter digit!");
                                } catch (Exception e) {
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "buy":
                                try{
                                    ExceptionHandling.isDigit(commend[1]);
                                    ShoppingCard shoppingCard = shoppingCardService.findById(Integer.valueOf(commend[1]));
                                    shoppingCard.setPayed(true);
                                    shoppingCardService.update(shoppingCard);
                                }catch (DigitException dd){
                                    System.out.println("enter digit!");
                                }catch (Exception e){
                                    System.out.println("wrong input!");
                                }
                                break;
                            case "help":
                                showCustomerMenu();
                                break;
                            case "logout":
                                state = false;
                                break;
                            case "exit":
                                state=false;
                                flag=false;
                                break;
                            default:
                                System.out.println("wong input!");
                                break;
                        }
                        break;
                    default:
                        System.out.println();
                        break;
                }
            }
        }
    }

    public static void showMenu() {
        System.out.println("registerAdmin userName password nationalCode");
        System.out.println("registerCustomer userName password Address");
        System.out.println("loginAdmin userName password");
        System.out.println("loginCustomer userName password");
    }

    public static void showAdminMenu() {
        System.out.println("showProduct");
        System.out.println("showCategory");
        System.out.println("addCategory title description categoryId");
        System.out.println("addProduct name description categoryId qty price");
        System.out.println("update productId newQty newPrice");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");
    }

    public static void showCustomerMenu() {
        System.out.println("showProduct");
        System.out.println("showCategory");
        System.out.println("showProductByCategoryId categoryId");
        System.out.println("showShoppingCard");
        System.out.println("addProductToCard productId shoppingCardId||(new) qty");
        System.out.println("buy shoppingCardId");
        System.out.println("help");
        System.out.println("logout");
        System.out.println("exit");
    }
}
