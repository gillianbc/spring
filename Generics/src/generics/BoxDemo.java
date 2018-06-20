package generics;

import java.util.List;

/** 
 * See https://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html
 * */
public class BoxDemo {

	  public static <U> void addBox(U u, 
	      List<Box<U>> boxes) {
	    Box<U> box = new Box<>();
	    box.set(u);
	    boxes.add(box);
	  }

	  public static <U> void outputBoxes(List<Box<U>> boxes) {
	    int counter = 0;
	    for (Box<U> box: boxes) {
	      U boxContents = box.get();
	      System.out.println("Box #" + counter + " contains [" +
	             boxContents.toString() + "]");
	      counter++;
	    }
	  }

	  public static void main(String[] args) {
	    java.util.ArrayList<Box<Integer>> listOfIntegerBoxes =
	      new java.util.ArrayList<>();
	    BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
	    BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
	    BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
	    BoxDemo.outputBoxes(listOfIntegerBoxes);
	  }
	}
