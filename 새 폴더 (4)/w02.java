import java.util.List;
import java.util.Scanner;

public class ShakeShackBurgerApplication {

    private static MenuContext menuContext;
    private static ManagementContext managementContext;

    public static void main(String[] args) {
        menuContext = new MenuContext();
        managementContext = new ManagementContext();
        displayMainMenu();
    }

    private static void displayMainMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");

        System.out.println("[ 메뉴 목록 ]");
        List<Menu> mainMenus = menuContext.getMenus("Main");
        printMenu(mainMenus, 1);

        System.out.println("[ 주문 목록 ]");
        List<Menu> orderMenus = menuContext.getMenus("Order");
        printMenu(orderMenus, mainMenus.size() + 1);

        handleMainMenuInput();
    }

    private static void printMenu(List<Menu> menus, int num) {
        for (int i = 0; i < menus.size(); i++, num++) {
            System.out.println(num + ". " + menus.get(i).name + "   | " + menus.get(i).description);
        }
    }

    private static void handleMainMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 0) {
            displayManagementMenu();
        } else if (input <= mainMenus.size()) {
            displayMenu(mainMenus.get(input - 1));
        } else if (input <= mainMenus.size() + orderMenus.size()) {
            int orderInput = input - mainMenus.size();
            switch (orderInput) {
                case 1:
                    displayOrderMenu();
                    break;
                case 2:
                    handleCancelMenuInput();
                    break;
                case 3:
                    handleListMenuInput();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    handleMainMenuInput();
            }
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleMainMenuInput();
        }
    }

    private static void displayManagementMenu() {
        System.out.println("SHAKESHACK BURGER 관리 메뉴에 오신걸 환영합니다.");
        System.out.println("아래 목록해서 원하는 명령을 골라 입력해주세요.\n");

        managementContext.displayMainMenu();

        handleCommandInput();
    }

    private static void handleCommandInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 0) {
            displayMainMenu();
        } else if (input >= 1 && input <= 4) {
            switch (input) {
                case 1:
                    managementContext.displayWaitingOrdersAndProcess();
                    break;
                case 2:
                    managementContext.displayCompletedOrders();
                    break;
                case 3:
                    String menuName = getMenuName();
                    Item newItem = managementContext.createMenuItem();
                    menuContext.addMenuItem(menuName, newItem);
                    break;
                case 4:
                    menuContext.displayAllItem();
                    System.out.print("삭제할 상품 ID: ");
                    int itemId = scanner.nextInt();
                    managementContext.deleteMenuItems(menuContext.getMenuItemMap(), itemId);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    handleCommandInput();
            }
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력
