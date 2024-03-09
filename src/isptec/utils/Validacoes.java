//package isptec.utils;
//
//import org.apache.commons.validator.routines.EmailValidator;
//
///**
// * A classe <b>Validacoes</b> implementa metodos uteis para confirmar a sintaxe
// * correcta de entidades como um endereco de email ou um num de telefone no
// * formato internnacional.
// *
// * @author Celso Paim
// * @version 1.0
// * @since 2020-03-30
// */
//public class Validacoes
//{
//
//	/**
//	 * Este metodo confirma se um endereco de email e valido com base na classe
//	 * org.apache.commons.validator.routines.EmailValidator
//	 *
//	 * @param email
//	 * @return boolean Representando se o email e valido ou nao.
//	 */
//	public static boolean isValidEmail(String email)
//	{
//		// create the EmailValidator instance
//		EmailValidator validator = EmailValidator.getInstance();
//
//		// check for valid email addresses using isValid method
//		return validator.isValid(email);
//	}
//
//	/**
//	 * Este metodo confirma se um endereco de email e valido com base em
//	 * expressoes regulares.
//	 *
//	 * @param endereco string que se quer validar
//	 * @return boolean Representando se o email e valido ou nao.
//	 */
//	public static boolean seEmailValido(String endereco)
//	{
//		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w-_]+\\.)+[\\w]+[\\w]$";
//		return endereco.matches(regex);
//	}
//
//	/**
//	 * Este metodo confirma se uma string representa um endereco de telemovel
//	 * valido. Inicialmente verifica-se se a string e vazia, se nao for, e feito
//	 * o trim() para de seguida validar se a string tem outros caracteres que
//	 * nao sejam digitos posteriormente verifica-se se o num esta no formato
//	 * nacional ou internacional e se tem os NDCs das operadoras Unitel e
//	 * Movicel caso seja um numero de Angola. Se for um numero internacional,
//	 * nao se faz qualquer validacao
//	 *
//	 * @param telemovel string que se quer validar
//	 * @return boolean Representando se o telemovel e valido ou nao.
//	 */
//	public static boolean seTelemovelValido(String telemovel)
//	{
//		boolean res;
//
//		if (telemovel == null)
//		{
//			return false;
//		}
//
//		String tel = "" + telemovel.replace(" ", "");
//		int tam = tel.length();
//
//		String regex = "[0-9]+";
//		if (!tel.matches(regex))
//		{
//			return false;
//		}
//
//		switch (tel.charAt(0))
//		{
//			case '2':
//				String prefixInter = tel.substring(0, 3);
//				if (prefixInter.equalsIgnoreCase("244"))
//				{
//					if (tam == 12)
//					{
//						String prefix = tel.substring(0, 5);
//						if (prefix.equalsIgnoreCase("24491") || prefix.equalsIgnoreCase("24492")
//							|| prefix.equalsIgnoreCase("24493") || prefix.equalsIgnoreCase("24494")
//							|| prefix.equalsIgnoreCase("24499"))
//						{
//							res = true;
//						}
//						else
//						{
//							res = false;
//						}
//					}
//					else
//					{
//						res = false;
//					}
//				}
//				else
//				{
//					res = true;
//				}
//				break;
//			case '9':
//				if (tam == 9)
//				{
//					String prefix = tel.substring(0, 2);
//					if (prefix.equalsIgnoreCase("91") || prefix.equalsIgnoreCase("92")
//						|| prefix.equalsIgnoreCase("93") || prefix.equalsIgnoreCase("94")
//						|| prefix.equalsIgnoreCase("99"))
//					{
//						res = true;
//					}
//					else
//					{
//						res = false;
//					}
//				}
//				else
//				{
//					res = false;
//				}
//				break;
//			default:
//				res = true;
//				break;
//		}
//		return res;
//	}
//}
