import java.util.Scanner;

public class Welcome {

    static final int NUM_BOOK = 3;
    static final int NUM_ITEM = 7;
    // static CartItem[] mCartItem = new CartItem[NUM_BOOK];
    // static int mCartCount = 0;
    static Cart mCart = new Cart();
    static User mUser;

    public static void main(String[] args) {

        // String[][] mBook = new String[NUM_BOOK][NUM_ITEM];
        Book[] mBookList = new Book[NUM_BOOK];

        Scanner input = new Scanner(System.in);
        System.out.print("당신의 이름을 입력하세요 : ");
        String userName = input.next();

        System.out.print("연락처를 입력하세요 : ");
        int userMobile = input.nextInt();

        mUser = new User(userName, userMobile);

        // System.out.println("Welcome to Shopping Mall");
        // System.out.println("Welcome to Book Market!");

        String greeting = "Welcome to Shopping Mall";
        String tagline = "Welcome to Book Market!";

        boolean quit = false;
        // int choice = 0;

        while (!quit) {

            System.out.println("***********************************************");
            System.out.println("\t" + greeting);
            System.out.println("\t" + tagline);

            menuIntroduction();
            System.out.print("메뉴 번호를 선택해주세요 ");
            int n = input.nextInt();

            if (n < 1 || n > 8) {
                System.out.println("1부터 9까지의 숫자를 입력하세요.");
            } else {

                switch (n) {
                    case 1:
                        menuGuestInfo(userName, userMobile);
                        break;
                    case 2:
                        menuCartItemList();
                        break;
                    case 3:
                        menuCartClear();
                        break;
                    case 4:
                        menuCartAddItem(mBookList);
                        break;
                    case 5:
                        menuCartRemoveItemCount();
                        break;
                    case 6:
                        menuCartRemoveItem();
                        break;
                    case 7:
                        menuCartBill();
                        break;
                    case 8:
                        input.close();
                        menuExit();
                        quit = true;
                        break;
                    case 9:
                        menuAdminLogin();
                        break;
                }
            }
        }
    }

    public static void menuIntroduction() {
        System.out.println("***********************************************");
        System.out.println(" 1. 고객 정보 확인하기 \t4. 바구니에 상품 추가하기");
        System.out.println(" 2. 장바구니 상품 목록 보기 \t5. 장바구니의 항목 수량 줄이기");
        System.out.println(" 3. 장바구니 비우기 \t6. 장바구니의 항목 삭제하기");
        System.out.println(" 7. 영수증 표시하기 \t8. 종료");
        System.out.println(" 9. 관리자 로그인");
        System.out.println("***********************************************");
    }

    public static void menuGuestInfo(String name, int mobile) {
        System.out.println("현재 고객 정보 : ");
        Person person = new Person(name, mobile);
        System.out.println("이름 " + person.getName() + "   연락처 " + person.getPhone());
    }

    public static void menuCartItemList() {
        if (mCart.mCartCount >= 0) {
            mCart.printCart();
        }
    }

    public static void menuCartClear() {
        if (mCart.mCartCount == 0)
            System.out.println("장바구니에 항목이 없습니다");
        else {
            System.out.println("장바구니의 모든 항목을 삭제하겠습니까? Y | N");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();

            if (str.toUpperCase().equals("Y")) {
                System.out.println("장바구니의 모든 항목을 삭제했습니다");
                mCart.deleteBook();
            }
        }
    }

    public static void menuCartAddItem(Book[] booklist) {
        BookList(booklist);

        mCart.printBookList(booklist);

        boolean quit = false;

        while (!quit) {
            System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 : ");

            Scanner input = new Scanner(System.in);
            String str = input.nextLine();

            boolean flag = false;
            int numId = -1;

            for (int i = 0; i < NUM_BOOK; i++)
                if (str.equals(booklist[i].getBookId())) {
                    numId = i;
                    flag = true;
                    break;
                }

            if (flag) {
                System.out.println("장바구니에 추가하겠습니까?  Y  | N ");
                str = input.nextLine();

                if (str.toUpperCase().equals("Y")) {
                    System.out.println(booklist[numId].getBookId() + " 도서가 장바구니에 추가되었습니다.");
                    if (!isCartInBook(booklist[numId].getBookId())) {
                        mCart.insertBook(booklist[numId]);
                    }
                }
                quit = true;
                input.close();
            } else
                System.out.println("다시 입력하세요.");
        }
    }

    public static void menuCartRemoveItemCount() {
        System.out.println("5. 장바구니에 항목 수량 줄이기");
    }

    public static void menuCartRemoveItem() {
        if (mCart.mCartCount == 0);
        System.out.println("장바구니에 항목이 없습니다");
        
        
            menuCartItemList();
            boolean quit = false;
            while (!quit) {
                System.out.print("장바구니에서 삭제할 도서의 ID를 입력하세요 : ");
                Scanner input = new Scanner(System.in);
                String str = input.nextLine();
                boolean flag = false;
                int numId = -1;

                for (int i = 0; i < mCart.mCartCount; i++) {
                    if (str.equals(mCart.mCartItem[i].getBookID())) {
                        numId = i;
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    System.out.println("장바구니의 항목을 삭제하겠습니까? Y | N");
                    str = input.nextLine();
                    if (str.toUpperCase().equals("Y")) {
                        System.out.println(mCart.mCartItem[numId].getBookID() + " 장바구니에서 도서가 삭제되었습니다.");
                        mCart.removeCart(numId);
                    }
                    quit = true;
                } else System.out.println("다시 입력해 주세요");
            }
        }

    

    public static void menuCartBill() {
        System.out.println("7. 영수증 표시하기");
    }

    public static void menuExit() {
        System.out.println("8. 종료");
    }

    public static void menuAdminLogin() {
        System.out.println("관리자 정보를 입력하세요");

        Scanner input = new Scanner(System.in);
        System.out.print("아이디 : ");
        String adminId = input.next();

        System.out.print("비밀번호 : ");
        String adminPW = input.next();

        Admin admin = new Admin(mUser.getName(), mUser.getPhone());
        if (adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
            System.out.println("이름 " + admin.getName() + " 연락처" + admin.getPhone());
            System.out.println("아이디 " + admin.getId() + " 비밀번호" + admin.getPassword());
        } else
            System.out.println("관리자 정보가 일치하지 않습니다.");

    }

    public static boolean isCartInBook(String bookId) {
        return mCart.isCartInBook(bookId);
    }

    public static void BookList(Book[] booklist) {
        booklist[0] = new Book("ISBN1234", " 쉽게 배우는 JSP 웹 프로그래밍", 27000);
        booklist[0].setAuthor("송미영");
        booklist[0].setDescription("단계별로 쇼핑몰을 구현하며 배우는 JSP 웹 프로그래밍");
        booklist[0].setCategory("IT전문서");
        booklist[0].setReleaseDate("2018/10/08");

        booklist[1] = new Book("ISBN1235", "안드로이드 프로그래밍", 33000);
        booklist[1].setAuthor("우재남");
        booklist[1].setDescription("실습 단계별 명쾌한 멘토링!");
        booklist[1].setCategory("IT전문서");
        booklist[1].setReleaseDate("2022/01/22");

        booklist[2] = new Book("ISBN1236", "스크래치", 22000);
        booklist[2].setAuthor("고광일");
        booklist[2].setDescription("컴퓨터 사고력을 키우는 블록 코딩");
        booklist[2].setCategory("컴퓨터입문");
        booklist[2].setReleaseDate("2019/06/10");
    }

}
     