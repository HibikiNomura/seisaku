package jp.ac.ohara.E.seisaku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jp.ac.ohara.E.seisaku.model.GakuseiHyouModel;
import jp.ac.ohara.E.seisaku.model.SeisekiHyouModel;
import jp.ac.ohara.E.seisaku.model.SyusekiHyouModel;
import jp.ac.ohara.E.seisaku.service.GakuseiService;
import jp.ac.ohara.E.seisaku.service.SeisekiService;
import jp.ac.ohara.E.seisaku.service.SyusekiService;

@Controller
public class MainController {
	//  private Object seisakuService;

	@Autowired
	private GakuseiService gakuseiservice;
	@Autowired
	private SeisekiService seisekiservice;
	@Autowired
	private SyusekiService syusekiservice;
	//登録前の画面
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("message", "こんにちは");
		return "top";
	}
	//登録後の画面
	@GetMapping("/top2")
	public String add2(GakuseiHyouModel gakuseihyou, ModelAndView model, @AuthenticationPrincipal GakuseiHyouModel loginUser) {
		model.addObject("gakuseihyou", gakuseihyou);
		model.addObject("gakuseihyou2",loginUser);
		model.setViewName("top2");
		return "top2";
	}

	//登録ページ
	@GetMapping("/from")
	public ModelAndView add(GakuseiHyouModel gakuseihyou, ModelAndView model) {
		model.addObject("gakuseihyou", gakuseihyou);
		model.setViewName("from");
		return model;
	}

	@PostMapping("/from")
	public String gakuseihyou(@Validated @ModelAttribute @NonNull GakuseiHyouModel gakuseihyou,
			RedirectAttributes result, ModelAndView model,
			RedirectAttributes redirectAttributes) {
		try {
			this.gakuseiservice.save(gakuseihyou);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/";

	}

	//成績表
	@GetMapping("/Grades")
	public ModelAndView add(SeisekiHyouModel seisekihyou, ModelAndView model) {
		model.addObject("seisekihyou", seisekihyou);
		model.setViewName("Grades");
		return model;
	}

	@PostMapping("/Grades")
	public String seisekihyou(@Validated @ModelAttribute @NonNull SeisekiHyouModel seisekihyou,
			RedirectAttributes result, ModelAndView model,
			RedirectAttributes redirectAttributes) {
		try {
			this.seisekiservice.save(seisekihyou);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/top2";

	}

	//出席表
	@GetMapping("/attendance")
	public ModelAndView add(SyusekiHyouModel syusekihyou, ModelAndView model) {
		model.addObject("syusekihyou", syusekihyou);
		model.setViewName("attendance");
		return model;
	}

	@PostMapping("/attendance")
	public String syusekihyou(@Validated @ModelAttribute @NonNull SyusekiHyouModel syusekihyou,
			RedirectAttributes result, ModelAndView model,
			RedirectAttributes redirectAttributes) {
		try {
			this.syusekiservice.save(syusekihyou);
			redirectAttributes.addFlashAttribute("exception", "");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
		}
		return "redirect:/top2";

	}

	//ログイン
	@PostMapping("/login")
	public String Login(@RequestParam("name") String name,
			@RequestParam("mail") String mail, RedirectAttributes redirectattributes) {
		if (gakuseiservice.login(name, mail)) {
			return "redirect:/top2";
		}
		redirectattributes.addFlashAttribute("error", "ログインに失敗しました");
		return "redirect:/login";
	}
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	//ログアウト
	@GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // セッションを無効にすることでログアウトさせる
        }
        return "redirect:/"; // ログインページにリダイレクト
    }
}
	


