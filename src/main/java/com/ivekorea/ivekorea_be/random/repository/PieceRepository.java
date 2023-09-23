package com.ivekorea.ivekorea_be.random.repository;

import com.ivekorea.ivekorea_be.random.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long> {

    List<Piece> findByMember_Uid(Long memberUid);

}
