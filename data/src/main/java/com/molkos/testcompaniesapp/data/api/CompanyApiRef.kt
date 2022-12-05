package com.molkos.testcompaniesapp.data.api

import com.molkos.testcompaniesapp.data.model.Company
import com.molkos.testcompaniesapp.data.model.CompanyInfo
import com.molkos.testcompaniesapp.domain.model.Response
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class CompanyApiRef(private val client: HttpClient) : CompanyApi {

    override suspend fun fetchCompaniesList(): Response<List<Company>> {
        return try {
            val res: Array<Company> =
                client.request(REQUEST_URL)
            Response.Success(res.toList())
        } catch (ex: ResponseException) {
            Response.Error("Bad response:$ex.response. Text:\"$ex.cachedResponseText\"")
        } catch (t: Throwable) {
            Response.Error(t.message.toString())
        }
    }

    override suspend fun fetchCompanyInfo(id: Int): Response<CompanyInfo> {
        return try {
            val res: Array<CompanyInfo> =
                client.request(REQUEST_URL) {
                    parameter(PARAMETER_ID, id)
                }
            Response.Success(res[0])
        } catch (ex: ResponseException) {
            Response.Error("Bad response:$ex.response. Text:\"$ex.cachedResponseText\"")
        } catch (t: Throwable) {
            Response.Error(t.message.toString())
        }
    }

    companion object {
        private const val REQUEST_URL = "https://lifehack.studio/test_task/test.php"
        private const val PARAMETER_ID = "id"
    }
}