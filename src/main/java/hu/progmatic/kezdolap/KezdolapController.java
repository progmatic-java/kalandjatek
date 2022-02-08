package hu.progmatic.kezdolap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KezdolapController {
  @RequestMapping("/")
  public String kezdolap() {
    return "/kalandjatek/beforemainpage";
  }
}
