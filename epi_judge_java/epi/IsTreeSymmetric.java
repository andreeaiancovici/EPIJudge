package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if(tree == null) {
      return true;
    }
    return checkSymmetric(tree.left, tree.right);
  }

  private static boolean checkSymmetric(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
    if(left == null && right == null) {
      return true;
    }
    if(left != null && right != null) {
      return left.data.equals(right.data) && checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
    }
    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
