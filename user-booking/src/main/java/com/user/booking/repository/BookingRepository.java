package com.user.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.booking.model.Booking;

/**
 * 
 * JPA Repository Interface for table booking responsible for DB Operation
 *
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {


	@Query("select c from Booking c where c.status = :status")
	public List<Booking> findbyStatus(@Param("status") String status);

	@Query("select c from Booking c where c.id = :id")
	public Booking findbyId(@Param("id") Long id);

	@Modifying
	@Query("update Booking u set u.status =:status WHERE u.id = :id")
	public int updateBooking(@Param("status") String status, @Param("id") Long id);

	@Modifying
	@Query("delete from Booking b where b.id=:id")
	void deleteBooking(@Param("id") Long id);
}
