package com.magfin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Time 2019/4/12
 * @Author zlian
 */
@RequestMapping("/access")
@Controller
public class AccessController {

    @RequestMapping("/shenghuodianqi")
    public ModelAndView shenghuodianqi(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","生活电器");
        return new ModelAndView("/access/ashenghuodianqi");
    }

    @RequestMapping("/gerenhuli")
    public ModelAndView gerenhuli(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","个人护理");
        return new ModelAndView("/access/bgerenhuli");
    }

    @RequestMapping("/pingbandiannao")
    public ModelAndView pingbandiannao(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","平板电脑");
        return new ModelAndView("/access/cpingbandiannao");
    }

    @RequestMapping("/shumaxiangji")
    public ModelAndView shumaxiangji(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","数码相机");
        return new ModelAndView("/access/dshumaxiangji");
    }

    @RequestMapping("/dianwandongman")
    public ModelAndView dianwandongman(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","电玩动漫");
        return new ModelAndView("/access/edianwandongman");
    }

    @RequestMapping("/shangwunanzhuang")
    public ModelAndView shangwunanzhuang(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","商务男装");
        return new ModelAndView("/access/fshangwunanzhuang");
    }

    @RequestMapping("/xiuxianyundong")
    public ModelAndView xiuxianyundong(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","休闲运动");
        return new ModelAndView("/access/gxiuxianyundong");
    }

    @RequestMapping("/xinping")
    public ModelAndView xinping(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","新品");
        return new ModelAndView("/access/hxinping");
    }

    @RequestMapping("/mingxingwanghong")
    public ModelAndView mingxingwanghong(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","明星网红");
        return new ModelAndView("/access/imingxingwanghong");
    }

    @RequestMapping("/shishangchuanda")
    public ModelAndView shishangchuanda(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","时尚穿搭");
        return new ModelAndView("/access/jshishangchuanda");
    }

    @RequestMapping("/yanchufu")
    public ModelAndView yanchufu(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","演出服");
        return new ModelAndView("/access/kyanchufu");
    }

    @RequestMapping("/yundongzhuang")
    public ModelAndView yundongzhuang(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","运动装");
        return new ModelAndView("/access/lyundongzhuang");
    }

    @RequestMapping("/lianyiqun")
    public ModelAndView lianyiqun(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","连衣裙");
        return new ModelAndView("/access/mlianyiqun");
    }

    @RequestMapping("/ganguo")
    public ModelAndView ganguo(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","干果");
        return new ModelAndView("/access/nganguo");
    }

    @RequestMapping("/latiao")
    public ModelAndView latiao(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","辣条");
        return new ModelAndView("/access/olatiao");
    }

    @RequestMapping("/shupian")
    public ModelAndView shupian(Model model, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("context","薯片");
        return new ModelAndView("/access/pshupian");
    }
}
