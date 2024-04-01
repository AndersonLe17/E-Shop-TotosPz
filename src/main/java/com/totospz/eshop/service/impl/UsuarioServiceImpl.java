package com.totospz.eshop.service.impl;

import com.totospz.eshop.config.exception.EntityNotFoundException;
import com.totospz.eshop.config.response.PaginationResponse;
import com.totospz.eshop.domain.dto.usuario.UsuarioEditReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRegReq;
import com.totospz.eshop.domain.dto.usuario.UsuarioRes;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.mapper.PaginationMapper;
import com.totospz.eshop.domain.mapper.PersonaMapper;
import com.totospz.eshop.domain.mapper.UsuarioMapper;
import com.totospz.eshop.domain.model.Perfil;
import com.totospz.eshop.domain.model.Persona;
import com.totospz.eshop.domain.model.Sede;
import com.totospz.eshop.domain.model.Usuario;
import com.totospz.eshop.domain.valid.usuario.UsuarioValid;
import com.totospz.eshop.repository.PerfilRepository;
import com.totospz.eshop.repository.PersonaRepository;
import com.totospz.eshop.repository.SedeRepository;
import com.totospz.eshop.repository.UsuarioRepository;
import com.totospz.eshop.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    // Repositories
    private final PersonaRepository personaRepository;
    private final UsuarioRepository usuarioRepository;
    private final SedeRepository sedeRepository;
    private final PerfilRepository perfilRepository;
    // Utils
    private final PasswordEncoder passwordEncoder;
    private final UsuarioValid usuarioValid;

    @Override
    @Transactional
    public UsuarioRes save(UsuarioRegReq usuReq, HttpServletRequest req) {
        // * Validations
        usuarioValid.validSave(usuReq);
        // * Validate Entities
        Sede sede = sedeRepository.findById(usuReq.getUsuSedCod())
                .orElseThrow(() -> new EntityNotFoundException("Sede", "Cod", usuReq.getUsuSedCod()));
        Perfil perfil = perfilRepository.findById(usuReq.getUsuPerfCod())
                .orElseThrow(() -> new EntityNotFoundException("Perfil", "Cod", usuReq.getUsuPerfCod()));
        // * Persistence
        Integer usuMod = (Integer) req.getAttribute("usuCod");
        Persona persona = personaRepository.save(PersonaMapper.personaMapper(usuReq.getUsuPer(), usuMod));
        Usuario usuario = usuarioRepository.save(UsuarioMapper.usuarioMapper(usuReq, persona, sede, perfil, passwordEncoder, usuMod));
        // * Return
        return UsuarioMapper.usuarioResponseMapper(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioRes findByCod(Integer usuCod) {
        Usuario usuario = usuarioRepository.findById(usuCod)
                .orElseThrow(() -> new EntityNotFoundException("Usuario", "Cod", usuCod));
        // * Return
        return UsuarioMapper.usuarioResponseMapper(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResponse findPagination(
            Pageable pageable, String usuNom, String perNom, Integer perfCod, Integer sedCod, Estado usuEst, HttpServletRequest req
    ) throws URISyntaxException {
        Page<Usuario> page = usuarioRepository.pageUsuario(usuNom, perNom, perfCod, sedCod, usuEst, pageable);
        List<UsuarioRes> data = page.map(u -> UsuarioMapper.usuarioResponseMapper(u)).stream().toList();
        // * Return
        return PaginationMapper.paginationResponseMapper(data, page, req);
    }

    @Override
    @Transactional
    public UsuarioRes update(UsuarioEditReq usuReq, Integer usuCod, HttpServletRequest req) {
        // * Validations
        Usuario usuario = usuarioRepository.findById(usuCod)
                .orElseThrow(() -> new EntityNotFoundException("Usuario", "Cod", usuCod));
        if (usuReq.getUsuPer() != null)
            usuarioValid.validUpdate(usuReq, usuario);
        // * Validate Entities
        Sede sede = !usuario.getUsuSed().getSedCod().equals(usuReq.getUsuSedCod())
                ? sedeRepository.findById(usuReq.getUsuSedCod())
                        .orElseThrow(() -> new EntityNotFoundException("Sede", "Cod", usuReq.getUsuSedCod()))
                : usuario.getUsuSed();
        Perfil perfil = !usuario.getUsuPerf().getPerfCod().equals(usuReq.getUsuPerfCod())
                ? perfilRepository.findById(usuReq.getUsuPerfCod())
                        .orElseThrow(() -> new EntityNotFoundException("Perfil", "Cod", usuReq.getUsuPerfCod()))
                : usuario.getUsuPerf();
        // * Persistence
        Integer usuMod = (Integer) req.getAttribute("usuCod");
        Persona persona = usuario.getUsuPer();
        if (usuReq.getUsuPer() != null)
            persona = personaRepository.save(PersonaMapper.personaMapper(usuReq.getUsuPer(), persona, usuMod));
        usuario = usuarioRepository.save(UsuarioMapper.usuarioMapper(usuReq, usuario, persona, sede, perfil, passwordEncoder, usuMod));
        // * Return
        return UsuarioMapper.usuarioResponseMapper(usuario);
    }


}
