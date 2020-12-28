package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    double[][] profits = new double[3][prices.size()];
    for (int j = 1; j <= 2; j++) {
      double minPrice = prices.get(0);
      for (int i = 1; i < prices.size(); i++) {
        minPrice = Math.min(minPrice, prices.get(i) - profits[j - 1][i]);
        profits[j][i] = Math.max(profits[j][i - 1], prices.get(i) - minPrice);
      }
    }
    return profits[2][prices.size() - 1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
