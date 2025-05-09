package main.main.task2;

public class Task 
{
  public StringBuilder task(String input)
  {
    StringBuilder result = new StringBuilder();
    boolean inside = false;
    for (int i = 0; i < input.length(); i++) 
    {
      char currentChar = input.charAt(i);
      if (currentChar == '*') 
      {      
        inside = !inside;
      } 
      else if (!inside) 
      {
        result.append(currentChar);
      }
    }
    return result;
  }

  public int countWords(String text) 
  {
    if (text.isEmpty()) 
    {
      return 0;
    }
    int wordCount = text.split("[,.\\s!?]+").length;
    return wordCount;
  }
}
