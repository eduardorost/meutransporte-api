package br.com.meutransporte.services;

import br.com.meutransporte.entities.EmpresaEntity;
import br.com.meutransporte.entities.EventoTransporteEntity;
import br.com.meutransporte.entities.PessoaEntity;
import br.com.meutransporte.entities.UsuarioEntity;
import br.com.meutransporte.exceptions.NaoAutorizadoException;
import br.com.meutransporte.models.HtmlTemplate;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.repositories.EventoTransporteRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.swing.text.MaskFormatter;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ListaPessoaTemplateService {

    @Autowired
    private EventoTransporteRepository eventoTransporteRepository;
    @Autowired
    private EmailService emailService;

    @Value("classpath:templates/lista-pessoa-DAER.html")
    private Resource listaPessoaDAERHtml;
    @Value("classpath:templates/lista-pessoa-METROPLAN.html")
    private Resource listaPessoaMETROPLANHtml;

    public HtmlTemplate getListaPessoaMETROPLANTemplate(Long eventoTransporteId, Usuario usuario) throws Exception {
        EventoTransporteEntity eventoTransporteEntity = eventoTransporteRepository.findOne(eventoTransporteId);

        if(!Objects.equals(usuario.getEmpresa().getId(), eventoTransporteEntity.getEmpresa().getId()))
            throw new NaoAutorizadoException();

        String template = getHtml(listaPessoaMETROPLANHtml)
                .replace("{{passageiros}}", buildPassageiros(eventoTransporteEntity.getPessoas()))
                .replace("{{contratada}}", buildContratada(eventoTransporteEntity.getEmpresa()))
                .replace("{{contratante}}", buildContratante(eventoTransporteEntity.getEvento().getUsuario()));

        emailService.sendEmail(usuario.getEmpresa().getEmail(), "LISTA EVENTO - "+eventoTransporteEntity.getEvento().getNome(), template);

        return new HtmlTemplate(template);
    }

    private String buildPassageiros(List<PessoaEntity> pessoas) throws ParseException {
        int maxTable = 26;
        return IntStream.rangeClosed(0, maxTable)
                .mapToObj(operand -> buildLinhaPassageiro(operand, pessoas))
                .collect(Collectors.joining());
    }

    private String buildLinhaPassageiro(int i, List<PessoaEntity> pessoas) {
            return "<tr style=\"height: 28px\">" +
                        buildLinhaPassageiroDados(i, pessoas) +
                    "</tr>";
    }

    private String buildLinhaPassageiroDados(int idx, List<PessoaEntity> pessoasEntitities) {
        try {
            return "<td style=\"border-bottom: 1px solid black; border-right: 1px solid black;border-left: 1px solid black;\">" + (idx+1) + "</td>" +
                    "<td style=\"border-bottom: 1px solid black; border-right: 1px solid black;\">" + pessoasEntitities.get(idx).getNome() + " - " + pessoasEntitities.get(idx).getTelefone() + "</td>" +
                    "<td style=\"border-bottom: 1px solid black; border-right: 1px solid black;\">" + pessoasEntitities.get(idx).getCpf() + "</td>";
        } catch (Exception ex) {
            return buildEmptyPassageiro(idx);
        }
    }

    private String buildEmptyPassageiro(int idx) {
        return "<td style=\"border-bottom: 1px solid black; border-right: 1px solid black;border-left: 1px solid black;\">"+idx+"</td>" +
                "<td style=\"border-bottom: 1px solid black; border-right: 1px solid black;\"></td>" +
                "<td style=\"border-bottom: 1px solid black; border-right: 1px solid black;\"></td>";
    }

    private String buildContratante(UsuarioEntity usuarioEntity) throws ParseException {
        if(usuarioEntity.getEmpresa() != null)
            return buildContratada(usuarioEntity.getEmpresa());

        return usuarioEntity.getPessoa().getNome() + " - " + usuarioEntity.getPessoa().getTelefone() + " - " + usuarioEntity.getPessoa().getCpf();
    }

    private String buildContratada(EmpresaEntity empresaEntity) throws ParseException {
        return empresaEntity.getNome() + " - " + empresaEntity.getCnpj();
    }

    private String getHtml(Resource resource) {
        try (InputStream is = resource.getInputStream()) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
