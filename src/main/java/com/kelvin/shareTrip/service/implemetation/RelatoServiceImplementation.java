package com.kelvin.shareTrip.service.implemetation;

import com.kelvin.shareTrip.model.Relato;
import com.kelvin.shareTrip.model.Destino;
import com.kelvin.shareTrip.repo.RelatoRepo;
import com.kelvin.shareTrip.repo.DestinoRepo;
import com.kelvin.shareTrip.service.RelatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class RelatoServiceImplementation implements RelatoService {
    @Autowired
    private RelatoRepo relatoRepo;

    @Autowired
    private DestinoRepo destinoRepo;

    @Override
    public List<Relato> getAllRelato() {
        return relatoRepo.findAll();
    }

    @Override
    public Relato getRelatoById(String id) {
        return relatoRepo.findById(id).orElse(null);
    }

    @Override
    public Relato addRelato(Relato relato) {
        if (relato.getNota() != null) {
            double nota = relato.getNota();
            if (nota < 0.0 || nota > 5.0) {
                throw new IllegalArgumentException("A nota deve estar entre 0 e 5.");
            }
        }
        Relato savedRelato = relatoRepo.save(relato);
        atualizarNotaDestino(relato.getDestino().getId());
        return savedRelato;
    }

//     @Override
// public Relato addRelato(Relato relato) {
//     if (relato.getNota() != null) {
//         double nota = relato.getNota();
//         if (nota < 0.0 || nota > 5.0) {
//             throw new IllegalArgumentException("A nota deve estar entre 0 e 5.");
//         }
//     }

//     if (relato.getDestino() == null) {
//         throw new IllegalArgumentException("O destino não pode ser nulo.");
//     }

//     if (relato.getDestino().getId() == null) {
//         // Supondo que você tenha um repositório para salvar o Destino
//         Destino savedDestino = destinoRepo.save(relato.getDestino());
//         relato.setDestino(savedDestino);
//     }

//     Relato savedRelato = relatoRepo.save(relato);
//     atualizarNotaDestino(relato.getDestino().getId());
//     return savedRelato;
// }


    @Override
    public Relato deleteRelato(String id) {
        Relato relatoDelete = relatoRepo.findById(id).orElse(null);
        relatoRepo.delete(relatoDelete);
        if (relatoDelete != null) {
            atualizarNotaDestino(relatoDelete.getDestino().getId());
        }
        return relatoDelete;
    }

    @Override
    public Relato updateRelato(String id, Relato relato) {
        Relato relatoUpdate = relatoRepo.findById(id).orElse(null);
        if (relatoUpdate != null) {
            if (relato.getNota() != null) {
                double nota = relato.getNota();
                if (nota < 0.0 || nota > 5.0) {
                    throw new IllegalArgumentException("A nota deve estar entre 0 e 5.");
                }
                relatoUpdate.setNota(nota);
            }
            relatoUpdate.setDescricao(relato.getDescricao());
            relatoUpdate.setFoto(relato.getFoto());
            relatoUpdate.setData(relato.getData());
            relatoRepo.save(relatoUpdate);
            atualizarNotaDestino(relato.getDestino().getId());
        }
        return relatoUpdate;
    }

    private void atualizarNotaDestino(String destinoId) {
    try {
        List<Relato> relatosDestino = relatoRepo.findByDestino_Id(destinoId);
        if (relatosDestino.isEmpty()) {
            System.out.println("Nenhum relato encontrado para o destino com ID: " + destinoId);
            return;
        }

        OptionalDouble nota = relatosDestino.stream()
                .mapToDouble(Relato::getNota)
                .average();

        Destino destino = destinoRepo.findById(destinoId).orElse(null);
        if (destino == null) {
            System.out.println("Destino não encontrado com ID: " + destinoId);
            return;
        }

        if (nota.isPresent()) {
            destino.setNota(nota.getAsDouble());
            destinoRepo.save(destino);
        } else {
            System.out.println("Nenhuma nota disponível para calcular a média.");
        }
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();
    }
}

}
