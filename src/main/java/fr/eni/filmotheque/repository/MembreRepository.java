package fr.eni.filmotheque.repository;

import fr.eni.filmotheque.bo.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreRepository extends JpaRepository<Membre, String> {
}
