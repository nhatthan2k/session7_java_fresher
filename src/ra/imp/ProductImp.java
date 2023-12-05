package ra.imp;

import ra.entity.Product;

import java.util.Scanner;

public class ProductImp {
    Product[] arrProduct = new Product[100];
    int currentIndex;

    public static void main(String[] args) {
        ProductImp productImp = new ProductImp();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("***********************MENU**************************");
            System.out.println("1. Nhập thông tin n sản phẩm (n nhập từ bàn phím)");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5. Thống kê các sản phẩm theo giá");
            System.out.println("6. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Nhập sản phẩm");
            System.out.println("8. Bán sản phẩm");
            System.out.println("9. Cập nhật trạng thái sản phẩm");
            System.out.println("10. Thoát");
            System.out.println("lựa chọn của bạn:");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    productImp.inputProduct(scanner);
                    break;
                case 2:
                    for (int i=0;i< productImp.currentIndex; i++){
                        productImp.arrProduct[i].displayData();
                    }
                    break;
                case 3:
                    productImp.outputProfit();
                    break;
                case 4:
                    productImp.profitArrangement();
                    break;
                case 5:
                    productImp.productsOfPrice(scanner);
                    break;
                case 6:
                    productImp.productSearch(scanner);
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    productImp.productStatus(scanner);
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("nhập lựa chon của bạn từ 1-10!");
            }

        }while (true);
    }

    public void inputProduct(Scanner scanner) {
        System.out.println("nhập số sản phẩm bạn muốn thêm vào:");
        int numProduct = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i<numProduct; i++) {
            System.out.printf("nhập thông tin sản phẩm thứ %d\n", i+1);
            arrProduct[currentIndex] = new Product();
            arrProduct[currentIndex].inputData(scanner, arrProduct, currentIndex);
            currentIndex++;
        }
    }
    public void outputProfit() {
        for (int i = 0; i < currentIndex; i++) {
            float profit = arrProduct[i].calProfit(arrProduct[i].getExportPrice(), arrProduct[i].getImportPrice());
            arrProduct[i].setProfit(profit);
        }
        System.out.println("tính lợi nhuận thành công");
    }
    public void profitArrangement() {
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = 1; j < currentIndex; j++) {
                if (arrProduct[i].getProfit() < arrProduct[j].getProfit()) {
                    Product temp = arrProduct[i];
                    arrProduct[i] = arrProduct[j];
                    arrProduct[j] = temp;
                }
            }
        }
        System.out.println("sắp xếp thành công!");
    }
    public void productsOfPrice (Scanner scanner) {
        System.out.println("nhâp khoảng giá bạn muốn tìm kiếm từ:");
        float fromPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("đến:");
        float toPrice = Float.parseFloat(scanner.nextLine());

        System.out.printf("danh sách các sản phẩm có giá bán trong khoảng giá từ %f đến %f là:\n", fromPrice, toPrice);
        for(int i=0; i<currentIndex; i++) {
            if(arrProduct[i].getExportPrice()>fromPrice && arrProduct[i].getExportPrice()<toPrice) {
                arrProduct[i].displayData();
            }
        }
    }
    public void productSearch(Scanner scanner) {
        System.out.println("nhập tên sản phẩm cần tìm kiếm: ");
        String inputSearch = scanner.nextLine();
        boolean isSearch = false;
        int productSearchIndex = -1;

        for (int i = 0; i < currentIndex; i++) {
            if (arrProduct[i].getProductName().equals(inputSearch)) {
                isSearch = true;
                productSearchIndex = i;
                break;
            }
        }

        if (isSearch) {
            System.out.println("thông tin sản phẩm: ");
            arrProduct[productSearchIndex].displayData();
        } else {
            System.out.println("không tìm thấy sản phẩm!");
        }
    }
    public void productStatus(Scanner scanner) {
        System.out.println("nhập mã sản phẩm bạn muốn cập nhật trang thái:");
        String inputProductId = scanner.nextLine();
        boolean isStatusUpdate = false;
        int statusUpdateIndex = -1;

        for (int i=0; i<currentIndex;i++) {
            if(arrProduct[i].getProductid().equals(inputProductId)){
                isStatusUpdate = true;
                statusUpdateIndex = i;
                break;
            }
        }

        if(isStatusUpdate) {
            arrProduct[statusUpdateIndex].setStatus(!arrProduct[statusUpdateIndex].isStatus());
        }else {
            System.out.println("không tồn tại mã sản phẩm");
        }
    }
}
