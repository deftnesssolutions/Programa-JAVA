package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class CPFValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException
	{
		if(value==null)
			return;
		String cpf = (String) value;
		if(cpf.length()!=11
			||!calcularDigitoVerificador(cpf.substring(0, 9)).equals(cpf.substring(9, 11)))
		{
			throw new ValidatorException(
			    new FacesMessage(FacesMessage.SEVERITY_ERROR,"CPF inválido.","Favor informar um CPF válido."));
			
		}
	}

	private String calcularDigitoVerificador(String num)
	{
		Integer primDig,segDig;
		int suma=0, peso=10;
		for(int i=0;i<num.length();i++)
		{
			suma+=Integer.parseInt(num.substring(i, i+1))*peso--;	
		}
		if(suma % 11==0|suma % 11==1)
		{
			primDig = new Integer(0);
		}else
		{
			primDig = new Integer(11 - (suma % 11));		
		}
		suma=0;
		peso=11;
		for(int i=0;i<num.length();i++)
		{
			suma+=Integer.parseInt(num.substring(i, i+1))*peso--;	
		}
		suma+= primDig.intValue()*2;
		if(suma % 11==0|suma % 11==1)
		{
			segDig = new Integer(0);
		}else
		{
			segDig = new Integer(11 - (suma % 11));		
		}
		return primDig.toString()+segDig.toString();
	}
}
