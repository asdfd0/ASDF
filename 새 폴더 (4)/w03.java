public class Item extends Menu {

    // 상품 가격
    private Double price;

    /**
     * 상품을 생성합니다.
     *
     * @param name 상품 이름
     * @param price 상품 가격
     * @param description 상품 설명
     */
    public Item(String name, Double price, String description) {

        super(name, description);


        this.price = price;
    }


    public Double getPrice() {
        return price;
    }
}