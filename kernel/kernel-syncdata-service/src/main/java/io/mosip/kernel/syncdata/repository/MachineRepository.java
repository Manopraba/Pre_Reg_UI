package io.mosip.kernel.syncdata.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.mosip.kernel.syncdata.entity.Machine;

/**
 * Repository function to fetching machine details
 * 
 * @author Megha Tanga
 * @since 1.0.0
 *
 */

@Repository
public interface MachineRepository extends JpaRepository<Machine, String> {
	/**
	 * Method to Machine details if the machine details is recently
	 * created,updated,deleted after lastUpdated timeStamp.
	 * 
	 * @param regCenterId      registration center id
	 * @param lastUpdated      timeStamp - last updated time
	 * @param currentTimeStamp - currentTimestamp
	 * @return list of {@link Machine} - list of machine
	 */
	@Query(value = "SELECT mm.id, mm.name, mm.mac_address, mm.serial_num, mm.ip_address, mm.mspec_id, mm.lang_code, mm.is_active, mm.cr_by, mm.cr_dtimes, mm.upd_by, mm.upd_dtimes, mm.is_deleted, mm.del_dtimes, mm.validity_end_dtimes,mm.key_index,mm.public_key,mm.zone_code,mm.regcntr_id from master.machine_master mm  where mm.regcntr_id = ?1 and ((mm.cr_dtimes >?2 AND mm.cr_dtimes <=?3) or (mm.upd_dtimes >?2 AND mm.upd_dtimes<=?3) or (mm.del_dtimes >?2 AND mm.del_dtimes<=?3))", nativeQuery = true)
	List<Machine> findAllLatestCreatedUpdateDeleted(String regCenterId, LocalDateTime lastUpdated,
			LocalDateTime currentTimeStamp);

	/**
	 * Method to fetch Machine by id
	 * 
	 * @param machineId id of the machine
	 * @return {@link Machine} - list of machine
	 */
	@Query(value = "SELECT mm.id, mm.name, mm.mac_address, mm.serial_num, mm.ip_address, mm.mspec_id, mm.lang_code, mm.is_active, mm.cr_by, mm.cr_dtimes, mm.upd_by, mm.upd_dtimes, mm.is_deleted, mm.del_dtimes, mm.validity_end_dtimes,mm.zone_code,mm.regcntr_id FROM master.machine_master mm where mm.id=?1 ", nativeQuery = true)
	List<Machine> findMachineById(String machineId);

	/**
	 * 
	 * @param machineId - machine id
	 * @return list of {@link Machine} - list of machine
	 */
	@Query(value = "SELECT mm.id, mm.name, mm.mac_address, mm.serial_num, mm.ip_address, mm.mspec_id, mm.lang_code, mm.is_active, mm.cr_by, mm.cr_dtimes, mm.upd_by, mm.upd_dtimes, mm.is_deleted, mm.del_dtimes, mm.validity_end_dtimes,mm.zone_code,mm.regcntr_id FROM master.machine_master mm where mm.id=?1 and mm.is_active=true ", nativeQuery = true)
	List<Machine> findByMachineIdAndIsActive(String machineId);

	/**
	 * Get machine by name
	 * 
	 * @param name machine name
	 * @return {@link Machine}
	 *//*
		 * @Query("FROM Machine m WHERE m.name=?1 and (m.isDeleted is null or m.isDeleted =false) and m.isActive = true"
		 * ) Optional<Machine> findByMachineNameActiveNondeleted(String name);
		 */
	@Query("From Machine m WHERE lower(m.name) = lower(?1)  and (m.isDeleted is null or m.isDeleted =false) and m.isActive = true")
	List<Machine> findByMachineNameAndIsActive(String machineName);

	@Query(value = "select distinct mm.regcntr_id , mm.id from  master.machine_master mm where mm.mac_address=?1 and mm.serial_num=?2 and mm.key_index=?3 and mm.is_active=true", nativeQuery = true)
	List<Object[]> getRegistrationCenterMachineWithMacAddressAndSerialNumAndKeyIndex(String macId, String serialNum,
			String keyIndex);

	@Query(value = "select distinct mm.regcntr_id , mm.id from  master.machine_master mm where mm.mac_address=?1  and mm.key_index=?2 and mm.is_active=true", nativeQuery = true)
	List<Object[]> getRegistrationCenterMachineWithMacAddressAndKeyIndex(String macId, String keyIndex);

	@Query(value = "select distinct mm.regcntr_id , mm.id from  master.machine_master mm where mm.serial_num=?1  and mm.key_index=?2 and mm.is_active=true", nativeQuery = true)
	List<Object[]> getRegistrationCenterMachineWithSerialNumberAndKeyIndex(String serialNum, String keyIndex);

	@Query(value = "select distinct mm.regcntr_id , mm.id from  master.machine_master mm where mm.mac_address=?1  and mm.serial_num=?2 and mm.is_active=true", nativeQuery = true)
	List<Object[]> getRegistrationCenterMachineWithMacAddressAndSerialNum(String macId, String serialNum);

	@Query(value = "select distinct mm.regcntr_id , mm.id from  master.machine_master mm where mm.key_index=?1 and mm.is_active=true", nativeQuery = true)
	List<Object[]> getRegistrationCenterMachineWithKeyIndex(String keyIndex);
	
	@Query(value = "select distinct mm.regcntr_id , mm.id from  master.machine_master mm where mm.mac_address=?1 and mm.is_active=true", nativeQuery = true)
	List<Object[]> getRegistrationCenterMachineWithMacAddress(String macId);
	
	@Query(value = "select distinct mm.regcntr_id , mm.id from  master.machine_master mm where mm.serial_num=?1 and mm.is_active=true", nativeQuery = true)
	List<Object[]> getRegistrationCenterMachineWithSerialNumber(String serialNum);
	
	@Query(value = "select *  from  master.machine_master mm where mm.regcntr_id=?1 and mm.id=?2 and mm.is_active=true", nativeQuery = true)
	List<Machine> getRegCenterIdWithRegIdAndMachineId(String regCenterId, String machineId);

	@Query("From Machine mm WHERE mm.regCenterId =?1 AND ((mm.createdDateTime > ?2 AND mm.createdDateTime<=?3) OR (mm.updatedDateTime > ?2 AND mm.updatedDateTime<=?3) OR (mm.deletedDateTime > ?2 AND mm.deletedDateTime<=?3))")
	List<Machine> findAllLatestCreatedUpdatedDeleted(String regCenterId, LocalDateTime lastUpdated,
			LocalDateTime currentTimeStamp);
}
