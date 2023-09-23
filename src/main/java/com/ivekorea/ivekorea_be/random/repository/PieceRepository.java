package com.ivekorea.ivekorea_be.random.repository;

import com.ivekorea.ivekorea_be.member.entity.Member;
import com.ivekorea.ivekorea_be.random.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PieceRepository extends JpaRepository<Piece, Long> {

    Optional<Piece> findByIdAndMember(Long id, Member member);
}