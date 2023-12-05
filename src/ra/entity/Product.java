package ra.entity;

import java.util.Scanner;

public class Product {
    private String productid;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {
    }

    public Product(String productid, String productName, float importPrice, float exportPrice, float profit, int quantity, String descriptions, boolean status) {
        this.productid = productid;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int currentIndex) {
//        Productid
        boolean isExitProductId = true;
        do {
            System.out.println("nhâp mã sản phẩm gồm 4 ký tự, bắt đầu là ký tự P: ");
            String inputProductId = scanner.nextLine();

            if (inputProductId.length() == 4 && inputProductId.startsWith("P")) {
                boolean isProductId = false;

                for (int i = 0; i < currentIndex; i++) {
                    if (arrProduct[i].getProductid().equals(inputProductId)) {
                        isProductId = true;
                        break;
                    }
                }

                if (!isProductId) {
                    this.productid = inputProductId;
                    isExitProductId = false;
                } else {
                    System.out.println("mã sản phẩm đã tồn tai");
                }

            } else {
                System.out.println("mã sản phẩm không hợp lệ! nhập lại mã sản phẩm");
            }

        } while (isExitProductId);

//      productName
        boolean isProductName = true;
        do {
            System.out.println("nhập tên sản phẩm phải có từ 6-50 ký tự:");
            String inputProductName = scanner.nextLine();

            if (inputProductName.length() > 6 && inputProductName.length() < 50) {
                this.productName = inputProductName;
                isProductName = false;
            } else {
                System.out.println("tên sản phẩm không hợp lệ! nhập lại tên sản phẩm");
            }

        } while (isProductName);

//        importPrice
        boolean isImportPrice = true;
        do {
            System.out.println("nhập giá nhập sản phẩm: ");
            float inputImportPrice = Float.parseFloat(scanner.nextLine());
            if (inputImportPrice > 0) {
                this.importPrice = inputImportPrice;
                isImportPrice = false;
            } else {
                System.out.println("giá nhâp phải lớn hơn 0");
            }
        } while (isImportPrice);


//      exportPrice
        boolean isExportPrice = true;
        do {
            System.out.println("nhập giá xuất của sản phẩm giá trị lớn hơn ít nhất 20% so với giá nhập:");
            float inputExportPrice = Float.parseFloat(scanner.nextLine());

            if (inputExportPrice > importPrice * 120 / 100) {
                this.exportPrice = inputExportPrice;
                isExportPrice = false;
            } else {
                System.out.println("Giá xuất có giá trị lớn hơn ít nhất 20% so với giá nhập");
            }

        } while (isExportPrice);

//      quantity
        boolean isQuantity = true;
        do {
            System.out.println("nhập số lượng sản phẩm: ");
            int inputQuantity = Integer.parseInt(scanner.nextLine());
            if (inputQuantity > 0) {
                this.quantity = inputQuantity;
                isQuantity = false;
            } else {
                System.out.println("số lượng sản phẩm phải lớn hơn 0");
            }
        } while (isQuantity);

//      descriptions
        System.out.println("nhập mô tả sản phẩm:");
        this.descriptions = scanner.nextLine();

//      status
        do {
            System.out.println("nhập trạng thái sản phẩm (true/false):");
            String inputStatus = scanner.nextLine();

            if (inputStatus.equals("true") || inputStatus.equals("false")) {
                this.status = Boolean.parseBoolean(inputStatus);
                break;
            } else {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập lại.");
            }

        } while (true);
    }

    public void displayData() {
        System.out.printf("mã sản phẩm: %s, tên sản phẩm: %s, giá nhập: %f, giá xuất: %f\n", this.productid, this.productName, this.importPrice, this.exportPrice);
        System.out.printf(" lợi nhập sách: %f, Số lượng sản phẩm: %d, mô tả: %s, trạng thái: %b\n", this.profit, this.quantity, this.descriptions, this.status);
    }

    public float calProfit(float exportPrice, float importPrice) {
        return exportPrice - importPrice;
    }
}
