package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    List<Integer> result = new ArrayList<>();
    boolean leftDone = false;
    while (tree != null) {
      if (!leftDone) {
        while (tree.left != null) {
          tree = tree.left;
        }
      }
      result.add(tree.data);
      leftDone = true;
      if (tree.right != null) {
        tree = tree.right;
        leftDone = false;
      } else if (tree.parent != null) {
        if (tree == tree.parent.left) {
          tree = tree.parent;
        } else {
          while (tree.parent != null && tree == tree.parent.right) {
            tree = tree.parent;
          }
          if (tree.parent == null) {
            break;
          }
          tree = tree.parent;
        }
      } else break;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
