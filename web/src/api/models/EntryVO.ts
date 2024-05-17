/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { EntrySource } from './EntrySource';
import type { UserVO } from './UserVO';
export type EntryVO = {
    collegeId?: number;
    collegeName?: string;
    createTime?: string;
    creatorInfo?: UserVO;
    description?: string;
    entryName?: string;
    id?: number;
    members?: Array<UserVO>;
    sources?: Array<EntrySource>;
    status?: number;
    statusRemarks?: string;
    updateTime?: string;
};

