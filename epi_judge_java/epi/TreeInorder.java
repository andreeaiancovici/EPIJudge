package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreeInorder {
  @EpiTest(testDataFile = "tree_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> inorder = new ArrayList<>();


    if(tree != null) {
      Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
      BinaryTreeNode<Integer> node = tree;
      while(!stack.isEmpty() || node != null) {
        if(node != null) {
          stack.push(node);
          node = node.left;
        } else {
          node = stack.pop();
          inorder.add(node.data);
          node = node.right;
        }
      }
    }
    return inorder;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
