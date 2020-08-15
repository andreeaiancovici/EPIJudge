package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SunsetView {
  static class Element {
    int value;
    int position;

    Element(int value, int position) {
      this.value = value;
      this.position = position;
    }
  }
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    Deque<Element> last = new LinkedList<>();
    int index = 0;
    while(sequence.hasNext()) {
      int value = sequence.next();
      Element peek = last.peek();
      if (peek == null) {
        last.push(new Element(value, index));
      } else if(peek.value > value) {
        last.push(new Element(value, index));
      } else {
        while (peek != null && peek.value <= value) {
          last.pop();
          peek = last.peek();
        }
        last.push(new Element(value, index));
      }
      index++;
    }
    List<Integer> results = new ArrayList<>();
    for(Element element : last) {
      results.add(element.position);
    }
    return results;
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
