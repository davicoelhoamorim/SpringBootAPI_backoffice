package br.com.springbootapi_backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springbootapi_backoffice.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}