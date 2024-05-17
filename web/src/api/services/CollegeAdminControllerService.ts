/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_boolean_ } from '../models/BaseResponse_boolean_';
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { CollegeAddRequest } from '../models/CollegeAddRequest';
import type { CollegeUpdateRequest } from '../models/CollegeUpdateRequest';
import type { CancelablePromise } from '@/api/core/CancelablePromise';
import { OpenAPI } from '@/api/core/OpenAPI';
import { request as __request } from '@/api/core/request';
export class CollegeAdminControllerService {
    /**
     * updateCollege
     * @param requestBody
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static updateCollegeUsingPut(
        requestBody?: CollegeUpdateRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/api/admin/college',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * addCollege
     * @param requestBody
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addCollegeUsingPost(
        requestBody?: CollegeAddRequest,
    ): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/admin/college',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * deleteCollege
     * @param collegeId collegeId
     * @returns BaseResponse_boolean_ OK
     * @throws ApiError
     */
    public static deleteCollegeUsingDelete(
        collegeId: number,
    ): CancelablePromise<BaseResponse_boolean_> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/admin/college/{collegeId}',
            path: {
                'collegeId': collegeId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
            },
        });
    }
}
