package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreePreorder {
  @EpiTest(testDataFile = "tree_preorder.tsv")

  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> inorder = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
    if (tree != null) {
      stack.push(tree);
      while (!stack.isEmpty()) {
        BinaryTreeNode<Integer> node = stack.pop();
        if (node != null) {
          inorder.add(node.data);
          stack.push(node.right);
          stack.push(node.left);
        }
      }
    }
    return inorder;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
