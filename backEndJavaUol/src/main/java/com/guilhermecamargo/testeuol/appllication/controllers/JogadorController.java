package com.guilhermecamargo.testeuol.appllication.controllers;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guilhermecamargo.testeuol.domain.entities.Jogador;
import com.guilhermecamargo.testeuol.domain.enums.EnumGrupo;
import com.guilhermecamargo.testeuol.domain.services.JogadorService;
import com.guilhermecamargo.testeuol.infra.exceptions.CodinomeExceptions;


@Controller
@RequestMapping(value = "/app")
public class JogadorController {
	
   @Autowired
   private JogadorService jogadorService;

   @GetMapping(value = "/jogadores")
   public ModelAndView listar(){
      ModelAndView mav = new ModelAndView("home/listagem");
      mav.addObject("jogadores", jogadorService.findAll());
      return  mav;
   }
   
   @GetMapping(value = "/novo")
   public ModelAndView cadastrar(@ModelAttribute("jogador") Jogador jogadorFlashAtrr){
      ModelAndView mav = new ModelAndView("home/jogador");
      mav.addObject("jogador", (Objects.nonNull(jogadorFlashAtrr)) ? jogadorFlashAtrr : new Jogador());
      mav.addObject("grupos", EnumGrupo.values());
      return  mav;
   }
   
   /**
    * método não utilizado na avaliação.
    * @param id
    * @return
    */
   @GetMapping(value = "/editar/{id}")
   public ModelAndView editar(@PathVariable("id") Long id){
      ModelAndView mav = new ModelAndView("home/jogador");
      mav.addObject("jogador", jogadorService.findById(id));
      return  mav;
   }
   
   @PostMapping(value = "/salvar", consumes="application/x-www-form-urlencoded")
   public String salvar(@ModelAttribute @Valid Jogador jogador, RedirectAttributes attributes){
      try{
          jogadorService.create(jogador);
          attributes.addFlashAttribute("sucesso", true);
          return "redirect:/app/novo";
      }catch (CodinomeExceptions e){
         attributes.addFlashAttribute("jogador", jogador);
         attributes.addFlashAttribute("erroCodinome", true);
         attributes.addFlashAttribute("grupos", EnumGrupo.values());
          return "redirect:/app/novo";
      }
   }

}
