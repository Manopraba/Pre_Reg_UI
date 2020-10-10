package io.mosip.kernel.masterdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import io.mosip.kernel.core.dataaccess.exception.DataAccessLayerException;
import io.mosip.kernel.core.util.EmptyCheckUtils;
import io.mosip.kernel.masterdata.constant.IndividualTypeErrorCode;
import io.mosip.kernel.masterdata.constant.MasterDataConstant;
import io.mosip.kernel.masterdata.dto.IndividualTypeDto;
import io.mosip.kernel.masterdata.dto.getresponse.IndividualTypeResponseDto;
import io.mosip.kernel.masterdata.dto.getresponse.PageDto;
import io.mosip.kernel.masterdata.dto.getresponse.extn.IndividualTypeExtnDto;
import io.mosip.kernel.masterdata.dto.request.FilterDto;
import io.mosip.kernel.masterdata.dto.request.FilterValueDto;
import io.mosip.kernel.masterdata.dto.request.SearchDto;
import io.mosip.kernel.masterdata.dto.response.ColumnValue;
import io.mosip.kernel.masterdata.dto.response.FilterResponseDto;
import io.mosip.kernel.masterdata.dto.response.PageResponseDto;
import io.mosip.kernel.masterdata.entity.IndividualType;
import io.mosip.kernel.masterdata.exception.DataNotFoundException;
import io.mosip.kernel.masterdata.exception.MasterDataServiceException;
import io.mosip.kernel.masterdata.exception.RequestException;
import io.mosip.kernel.masterdata.repository.IndividualTypeRepository;
import io.mosip.kernel.masterdata.service.IndividualTypeService;
import io.mosip.kernel.masterdata.utils.AuditUtil;
import io.mosip.kernel.masterdata.utils.ExceptionUtils;
import io.mosip.kernel.masterdata.utils.MapperUtils;
import io.mosip.kernel.masterdata.utils.MasterDataFilterHelper;
import io.mosip.kernel.masterdata.utils.MasterdataCreationUtil;
import io.mosip.kernel.masterdata.utils.MasterdataSearchHelper;
import io.mosip.kernel.masterdata.utils.MetaDataUtils;
import io.mosip.kernel.masterdata.utils.PageUtils;
import io.mosip.kernel.masterdata.validator.FilterColumnValidator;
import io.mosip.kernel.masterdata.validator.FilterTypeValidator;

/**
 * @author Bal Vikash Sharma
 * @author Sidhant Agarwal
 * 
 * @since 1.0.0
 *
 */
@Service
public class IndividualTypeServiceImpl implements IndividualTypeService {

	@Autowired
	private IndividualTypeRepository individualTypeRepository;

	@Autowired
	FilterTypeValidator filterTypeValidator;

	@Autowired
	FilterColumnValidator filterColumnValidator;

	@Autowired
	MasterdataSearchHelper masterDataSearchHelper;

	@Autowired
	MasterDataFilterHelper masterDataFilterHelper;

	@Autowired
	private PageUtils pageUtils;
	
	@Autowired
	private MasterdataCreationUtil masterdataCreationUtil;

	@Autowired
	private AuditUtil auditUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.mosip.kernel.masterdata.service.IndividualTypeService#
	 * getAllIndividualTypes()
	 */
	@Override
	public IndividualTypeResponseDto getAllIndividualTypes() {
		IndividualTypeResponseDto responseDto = new IndividualTypeResponseDto();
		try {
			List<IndividualType> list = individualTypeRepository.findAll();
			if (!EmptyCheckUtils.isNullEmpty(list)) {
				for (IndividualType individualType : list) {
					responseDto.getIndividualTypes().add(MapperUtils.map(individualType, new IndividualTypeDto()));
				}
			} else {
				throw new DataNotFoundException(
						IndividualTypeErrorCode.NO_INDIVIDUAL_TYPE_FOUND_EXCEPTION.getErrorCode(),
						IndividualTypeErrorCode.NO_INDIVIDUAL_TYPE_FOUND_EXCEPTION.getErrorMessage());
			}
		} catch (DataAccessException | DataAccessLayerException e) {
			throw new MasterDataServiceException(IndividualTypeErrorCode.INDIVIDUAL_TYPE_FETCH_EXCEPTION.getErrorCode(),
					IndividualTypeErrorCode.INDIVIDUAL_TYPE_FETCH_EXCEPTION.getErrorMessage());
		}
		return responseDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.mosip.kernel.masterdata.service.IndividualTypeService#getIndividualTypes(
	 * int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public PageDto<IndividualTypeExtnDto> getIndividualTypes(int pageNumber, int pageSize, String sortBy,
			String orderBy) {
		List<IndividualTypeExtnDto> individualTypes = null;
		PageDto<IndividualTypeExtnDto> pageDto = null;
		try {
			Page<IndividualType> pageData = individualTypeRepository
					.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Direction.fromString(orderBy), sortBy)));
			if (pageData != null && pageData.getContent() != null && !pageData.getContent().isEmpty()) {
				individualTypes = MapperUtils.mapAll(pageData.getContent(), IndividualTypeExtnDto.class);
				pageDto = new PageDto<>(pageData.getNumber(), pageData.getTotalPages(), pageData.getTotalElements(),
						individualTypes);
			} else {
				throw new DataNotFoundException(
						IndividualTypeErrorCode.NO_INDIVIDUAL_TYPE_FOUND_EXCEPTION.getErrorCode(),
						IndividualTypeErrorCode.NO_INDIVIDUAL_TYPE_FOUND_EXCEPTION.getErrorMessage());
			}
		} catch (DataAccessException | DataAccessLayerException e) {
			throw new MasterDataServiceException(IndividualTypeErrorCode.INDIVIDUAL_TYPE_FETCH_EXCEPTION.getErrorCode(),
					IndividualTypeErrorCode.INDIVIDUAL_TYPE_FETCH_EXCEPTION.getErrorMessage());
		}
		return pageDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.mosip.kernel.masterdata.service.IndividualTypeService#searchIndividuals(io
	 * .mosip.kernel.masterdata.dto.request.SearchDto)
	 */
	@Override
	public PageResponseDto<IndividualTypeExtnDto> searchIndividuals(SearchDto dto) {
		PageResponseDto<IndividualTypeExtnDto> pageDto = new PageResponseDto<>();
		List<IndividualTypeExtnDto> individuals = null;
		if (filterTypeValidator.validate(IndividualTypeExtnDto.class, dto.getFilters())) {
			pageUtils.validateSortField(IndividualType.class, dto.getSort());
			Page<IndividualType> page = masterDataSearchHelper.searchMasterdata(IndividualType.class, dto, null);
			if (page.getContent() != null && !page.getContent().isEmpty()) {
				pageDto = PageUtils.pageResponse(page);
				individuals = MapperUtils.mapAll(page.getContent(), IndividualTypeExtnDto.class);
				pageDto.setData(individuals);
			}
		}
		return pageDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.mosip.kernel.masterdata.service.IndividualTypeService#
	 * individualsFilterValues(io.mosip.kernel.masterdata.dto.request.
	 * FilterValueDto)
	 */
	@Override
	public FilterResponseDto individualsFilterValues(FilterValueDto filterValueDto) {
		FilterResponseDto filterResponseDto = new FilterResponseDto();
		List<ColumnValue> columnValueList = new ArrayList<>();
		if (filterColumnValidator.validate(FilterDto.class, filterValueDto.getFilters(), IndividualType.class)) {
			for (FilterDto filterDto : filterValueDto.getFilters()) {
				List<?> filterValues = masterDataFilterHelper.filterValues(IndividualType.class, filterDto,
						filterValueDto);
				filterValues.forEach(filterValue -> {
					ColumnValue columnValue = new ColumnValue();
					columnValue.setFieldID(filterDto.getColumnName());
					columnValue.setFieldValue(filterValue.toString());
					columnValueList.add(columnValue);
				});
			}
			filterResponseDto.setFilters(columnValueList);
		}
		return filterResponseDto;
	}

	@Override
	public IndividualTypeExtnDto createIndividualsTypes(IndividualTypeDto individualTypeDto) {
		IndividualType entity = null;
		try {
			individualTypeDto = masterdataCreationUtil.createMasterData(IndividualType.class, individualTypeDto);
			entity = MetaDataUtils.setCreateMetaData(individualTypeDto, IndividualType.class);
			entity =individualTypeRepository.create(entity);
		} catch (DataAccessLayerException | DataAccessException | IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {
			auditUtil.auditRequest(
					String.format(MasterDataConstant.FAILURE_CREATE, IndividualType.class.getSimpleName()),
					MasterDataConstant.AUDIT_SYSTEM,
					String.format(MasterDataConstant.FAILURE_DESC,
							IndividualTypeErrorCode.INDIVIDUAL_TYPE_INSERT_EXCEPTION.getErrorCode(),
							IndividualTypeErrorCode.INDIVIDUAL_TYPE_INSERT_EXCEPTION.getErrorMessage()
									+ ExceptionUtils.parseException(e)));
			throw new MasterDataServiceException(
					IndividualTypeErrorCode.INDIVIDUAL_TYPE_INSERT_EXCEPTION.getErrorCode(),
					IndividualTypeErrorCode.INDIVIDUAL_TYPE_INSERT_EXCEPTION.getErrorMessage() + " "
							+ ExceptionUtils.parseException(e));
		}
		
		IndividualTypeExtnDto individualTypeExtnDto = new IndividualTypeExtnDto();

		MapperUtils.map(entity, individualTypeExtnDto);
		auditUtil.auditRequest(
				String.format(MasterDataConstant.SUCCESSFUL_CREATE, IndividualType.class.getSimpleName()),
				MasterDataConstant.AUDIT_SYSTEM, String.format(MasterDataConstant.SUCCESSFUL_CREATE_DESC,
						IndividualType.class.getSimpleName(), individualTypeExtnDto.getCode()));
		return individualTypeExtnDto;
	}

	@Override
	public IndividualTypeExtnDto updateIndividualsTypes(IndividualTypeDto individualTypeDto) {
		IndividualTypeExtnDto individualTypeExtnDto = new IndividualTypeExtnDto();
		try {
			IndividualType individualType = individualTypeRepository.findIndividualTypeByCodeAndLangCode(individualTypeDto.getCode(),individualTypeDto.getLangCode());
			if (!EmptyCheckUtils.isNullEmpty(individualType)) {
				individualTypeDto = masterdataCreationUtil.updateMasterData(IndividualType.class, individualTypeDto);
				MetaDataUtils.setUpdateMetaData(individualTypeDto, individualType, false);
				individualTypeRepository.update(individualType);
				MapperUtils.map(individualType, individualTypeExtnDto);

				if(!individualTypeDto.getIsActive()) {
					masterdataCreationUtil.updateMasterDataDeactivate(IndividualType.class, individualTypeDto.getCode());
				}
			} else {
				throw new RequestException(IndividualTypeErrorCode.NO_INDIVIDUAL_TYPE_FOUND_EXCEPTION.getErrorCode(),
						IndividualTypeErrorCode.NO_INDIVIDUAL_TYPE_FOUND_EXCEPTION.getErrorMessage());
			}
		} catch (DataAccessLayerException | DataAccessException | IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException e) {
			auditUtil.auditRequest(
					String.format(MasterDataConstant.FAILURE_UPDATE, IndividualType.class.getSimpleName()),
					MasterDataConstant.AUDIT_SYSTEM,
					String.format(MasterDataConstant.FAILURE_DESC,
							IndividualTypeErrorCode.INDIVIDUAL_TYPE_UPDATE_EXCEPTION.getErrorCode(),
							IndividualTypeErrorCode.INDIVIDUAL_TYPE_UPDATE_EXCEPTION.getErrorMessage()
									+ ExceptionUtils.parseException(e)));
			throw new MasterDataServiceException(IndividualTypeErrorCode.INDIVIDUAL_TYPE_UPDATE_EXCEPTION.getErrorCode(),
					IndividualTypeErrorCode.INDIVIDUAL_TYPE_UPDATE_EXCEPTION.getErrorMessage() + ExceptionUtils.parseException(e));
		}

		auditUtil.auditRequest(
				String.format(MasterDataConstant.SUCCESSFUL_UPDATE, IndividualType.class.getSimpleName()),
				MasterDataConstant.AUDIT_SYSTEM, String.format(MasterDataConstant.SUCCESSFUL_UPDATE_DESC,
						IndividualType.class.getSimpleName(), individualTypeExtnDto.getCode()));
		return individualTypeExtnDto;
	}
	
}
	