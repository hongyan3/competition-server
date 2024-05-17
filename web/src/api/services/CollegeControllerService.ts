/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_Page_CollegeVO_ } from '../models/BaseResponse_Page_CollegeVO_';
import type { CancelablePromise } from '@/api/core/CancelablePromise';
import { OpenAPI } from '@/api/core/OpenAPI';
import { request as __request } from '@/api/core/request';
export class CollegeControllerService {
    /**
     * getCollegeList
     * @param collegeName
     * @param current
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @returns BaseResponse_Page_CollegeVO_ OK
     * @throws ApiError
     */
    public static getCollegeListUsingGet(
        collegeName?: string,
        current?: number,
        pageSize?: number,
        sortField?: string,
        sortOrder?: string,
    ): CancelablePromise<BaseResponse_Page_CollegeVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/college',
            query: {
                'collegeName': collegeName,
                'current': current,
                'pageSize': pageSize,
                'sortField': sortField,
                'sortOrder': sortOrder,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
}
