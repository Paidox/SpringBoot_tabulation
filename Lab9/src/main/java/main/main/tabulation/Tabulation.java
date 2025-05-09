package main.main.tabulation;

public class Tabulation 
{
 
  private static final double EPS = 0.001;

  public void tabul(double a, double b, double h, double t) 
  {
    int n = (int) ((b - a) / h + 1);

    for (int i = 0; i < n; i++)
     {
      double x = a + i * h;
      double result;

      if (x <= 0.9 + EPS)
      {
        if (x > EPS && (x + t) >= EPS) 
        {  
          result = (Math.pow(Math.log(x), 3) + x * x) / Math.sqrt(x + t);
        } 
        else 
        {
          System.out.printf("x = %.6f, y = no%n", x);
          continue;
        }
      } 
      else 
      {
        result = Math.cos(x) + t * Math.sin(x) * Math.sin(x);
      }

      System.out.printf("x = %.6f, y = %.6f%n", x, result);
    }
  }

  public double fun(double x, double t) 
  {
    if (x <= 0.9 + EPS) 
    {
      return (Math.pow(Math.log(x), 3) + x * x) / Math.sqrt(x + t);
    } 
    else 
    {
      return Math.cos(x) + t * (Math.sin(x) * Math.sin(x));
    }
  }

  public int countStep(double start, double finish, double step)
  {
    return (int) ((finish - start) / step) + 1;
  }

  public double[] generateXArray(double start, double finish, double step)
  {
    int n = countStep(start, finish, step); 
    double[] xArray = new double[n];

    for (int i = 0; i < n; i++) 
    {
      xArray[i] = start + i * step;
    }
    return xArray;
  }
    
  public double[] generateYArray(double[] xArray, double x, double t)
  {
    double[] yArray = new double[xArray.length];

    for (int i = 0; i < xArray.length; i++) 
    {
      yArray[i] = fun(xArray[i], t);
    }
    return yArray;
  }

  public int minElement(double[] yArray)
  {
    int minInd = 0;

    for(int i = 0; i < yArray.length; i++)
    {
      if(yArray[i] < yArray[minInd])
      {
        minInd = i;
      }
    }
    return minInd;
  }

  public int maxElement(double[] yArray)
  {
    int maxInd = 0;

    for(int i = 0; i < yArray.length; i++)
    {
      if(yArray[i] > yArray[maxInd])
      {
        maxInd = i;
      }
    }

    return maxInd;
    
  }

  public double sum(double[] yArray)
  {
    double sum = 0;

    for(int i = 0; i < yArray.length; i++)
    {
      sum += yArray[i];
    }

    return sum ;
  }

  public double average(double[] yArray)
  {
    double sum = 0;
    int count = 0;

    for(int i = 0; i < yArray.length; i++)
    {
      sum += yArray[i];
      count++;
    }

    return count == 0 ? 0 : sum / count;
  }
}