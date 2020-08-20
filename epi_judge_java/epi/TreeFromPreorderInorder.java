package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.concurrent.atomic.*;

public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    preIndex = 0;
    Map<Integer, Integer> inOrderMap = new HashMap<>();
    for(int i = 0; i < inorder.size(); i++) {
      inOrderMap.put(inorder.get(i), i);
    }
    return buildTree(inOrderMap, preorder, 0, inorder.size() - 1);
  }

  private static int preIndex;

  private static BinaryTreeNode<Integer> buildTree(Map<Integer, Integer> inOrderMap, List<Integer> preorder, int start, int end) {
    if (start > end) {
      return null;
    }
    BinaryTreeNode<Integer> node = new BinaryTreeNode<>(preorder.get(preIndex));
    preIndex++;
    if(start == end) {
      return node;
    }
    int inIndex = inOrderMap.get(node.data);
    node.left = buildTree(inOrderMap, preorder, start, inIndex - 1);
    node.right = buildTree(inOrderMap, preorder, inIndex + 1, end);
    return node;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
