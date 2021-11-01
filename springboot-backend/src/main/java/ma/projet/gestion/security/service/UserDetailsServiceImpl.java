package ma.projet.gestion.security.service;

import ma.projet.gestion.data.model.connection.UtilisateurDao;
import ma.projet.gestion.data.repository.UserRepository;
import ma.projet.gestion.service.model.UserDTO;
import ma.projet.gestion.util.ModelConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelConverterUtil modelConverterUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurDao userDAO = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        UserDTO userDTO = modelConverterUtil.getUserDTO(userDAO);

        return UserDetailsImpl.build(userDTO);
    }

}
