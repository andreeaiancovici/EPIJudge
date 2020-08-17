package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
     return inOrder(0, tree);
  }

  private static int inOrder(int sum, BinaryTreeNode<Integer> tree) {
    if(tree == null) {
      return 0;
    } else {
      sum *= 2;
      sum += tree.data;
      if(tree.left == null && tree.right == null) {
        return sum;
      }
      return inOrder(sum, tree.left) + inOrder(sum, tree.right);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
