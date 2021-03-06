package io.mosip.kernel.masterdata.dto;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author Megha Tanga
 * @version 1.0.0
 */
@Data
public class LocationHierarchyLevelResponseDto {
	private List<LocationHierarchyLevelDto> locationHierarchyLevels;
}
