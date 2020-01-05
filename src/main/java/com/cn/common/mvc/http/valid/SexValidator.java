package com.cn.common.mvc.http.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class SexValidator implements ConstraintValidator<SexConstraint, String> {

   private String[] sexs = null;

   public void initialize(SexConstraint constraint) {
      this.sexs = constraint.sexs();
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      for(String sex : sexs){
         if(Objects.equals(sex,obj)){
            return true;
         }
      }
      return false;
   }
}
