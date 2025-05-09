package main.main.controller;

import jakarta.servlet.http.HttpSession;
import main.main.tabulation.Tabulation;
import main.main.task2.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerApp 
{

  @GetMapping("/index")
  public String showForm(Model model) 
  {
    model.addAttribute("gifUrl", "/images/lol.gif");
    return "index";
  }

  @PostMapping("/calculate")
  public String calculate(@RequestParam(required = false) Double a,
                          @RequestParam(required = false) Double b,
                          @RequestParam(required = false) Double h,
                          @RequestParam(required = false) Double t,
                          @RequestParam(required = false) String input,
                          HttpSession session,
                          Model model) {

    if (a == null || b == null || h == null || t == null) 
    {
      model.addAttribute("gifUrl", "/images/exept.gif");
      return "invalid";
    }

    Tabulation tabulation = new Tabulation();
    Task task = new Task();

    double[] xArray = tabulation.generateXArray(a, b, h);
    double[] yArray = tabulation.generateYArray(xArray, a, t);

    double minValue = yArray[tabulation.minElement(yArray)];
    double maxValue = yArray[tabulation.maxElement(yArray)];
    double average = tabulation.average(yArray);

    String resultText = task.task(input).toString();
    int count = task.countWords(resultText);

    session.setAttribute("min", minValue);
    session.setAttribute("max", maxValue);
    session.setAttribute("average", average);
    session.setAttribute("text", resultText);
    session.setAttribute("wordCount", count);

    return "redirect:/result";
  }

  @GetMapping("/result")
  public String showResult(Model model, HttpSession session) 
  {
    model.addAttribute("min", session.getAttribute("min"));
    model.addAttribute("max", session.getAttribute("max"));
    model.addAttribute("average", session.getAttribute("average"));
    model.addAttribute("text", session.getAttribute("text"));
    model.addAttribute("wordCount", session.getAttribute("wordCount"));
    model.addAttribute("gifUrl", "/images/lol.gif");
    return "result";
  }
}