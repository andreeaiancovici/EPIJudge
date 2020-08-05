package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DirectoryPathNormalization {
  @EpiTest(testDataFile = "directory_path_normalization.tsv")

  public static String shortestEquivalentPath(String path) {
    StringBuilder normalizedPath = new StringBuilder();
    if (path.startsWith("/")) {
      normalizedPath.append("/");
    }
    Deque<String> folders = new LinkedList<>();
    String[] items = path.split("/");
    for (String item : items) {
      if (item.equals("..")) {
        String lastItem = folders.peek();
        if (lastItem != null && !lastItem.equals("..")) {
          folders.pop();
        } else {
          folders.push(item);
        }
      } else if (!item.equals(".") && !item.isEmpty()) {
        folders.push(item);
      }
    }
    if (!folders.isEmpty()) {
      Iterator<String> it = folders.descendingIterator();
      normalizedPath.append(it.next());
      while (it.hasNext()) {
        normalizedPath.append("/");
        normalizedPath.append(it.next());
      }
    }
    return normalizedPath.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DirectoryPathNormalization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
