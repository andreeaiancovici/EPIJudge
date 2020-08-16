package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.concurrent.atomic.*;

public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    if(tree == null) {
      return true;
    }
    if(!isBalanced(tree.left)) {
      return false;
    }
    int leftHeight = computeHeight(tree.left);
    if(!isBalanced(tree.right)) {
      return false;
    }
    int rightHeight = computeHeight(tree.right);

    return Math.abs(leftHeight - rightHeight) <= 1;
  }

  private static int computeHeight(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return 0;
    }
    return Math.max(computeHeight(tree.left), computeHeight(tree.right)) + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
