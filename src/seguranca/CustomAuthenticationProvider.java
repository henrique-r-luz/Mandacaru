package seguranca;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import controle.ldap.*;
import controle.quota.Label;
import dao.*;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	// @Autowired
	// private UserDao userDao;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

		String username = String.valueOf(auth.getPrincipal());
		String password = String.valueOf(auth.getCredentials());

		/*
		 * User user = userDao.getByUsernameAndPassword(username, password);
		 * 
		 * if (user == null) { throw new
		 * BadCredentialsException("Bad Credentials"); }
		 * 
		 * if (!(username.equals(user.getUsername) &&
		 * password.equals(user.getPassword))) { throw new
		 * BadCredentialsException("Bad Credentials"); }
		 * 
		 * List<GrantedAuthority> authorities = user.getAuthorities();
		 */

		// PARA FINS DE TESTE ESTOU COMPARANDO DIRETO COM UMA STRING
		ConectLdap conectLdap = new ConectLdap();
		conectLdap.setUsuario(username);
		// Md5.transMd5(senha)
		conectLdap.setSenha(password);
		// verificar servido ldap
		if (!(new DAOqueryUsuario().pesquisaNome(username).isEmpty())) {
			if ((new DAOqueryUsuario().AltenticaUsruario(username,
					Md5.transMd5(password)))) {
				// definir role do usuário

			} else {
				if (!(conectLdap.testaConexao())) {
					throw new BadCredentialsException(
							Label.getConexaoLdap());
				} else {
					if (!(conectLdap.conexaoLdap())) {
						throw new BadCredentialsException(
								Label.getUsuarioSenha());
					} else {
						// definir role do usuário

					}
				}
			}
		}else{
			throw new BadCredentialsException(
					Label.getUsuarioCadastrado());
		}

		/*
		 * if (!(conectLdap.testaConexao())) { throw new
		 * BadCredentialsException(
		 * "O sistema não pode realizar conexão com LDAP!!"); } else { if
		 * (!(conectLdap.conexaoLdap())) { throw new BadCredentialsException(
		 * "Usuário ou senha estão errados"); }
		 * 
		 * } if (!(conectLdap.conexaoLdap() || new DAOqueryUsuario()
		 * .AltenticaUsruario(username, Md5.transMd5(password)))) { throw new
		 * BadCredentialsException( "Usuário ou senha estão errados"); } }
		 */

		Authentication token = new UsernamePasswordAuthenticationToken(
				username, password, altentica(username));
		/*List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		Authentication token = new UsernamePasswordAuthenticationToken(
						username, password, authorities);*/
		return token;
	}

	public List<GrantedAuthority> altentica(String username) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(new DAOqueryUsuario().getUsuario(username).getGrupo().getNome()));
		return authorities;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}