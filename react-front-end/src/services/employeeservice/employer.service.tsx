import axios from 'axios';
import { Util } from '../../utils/Util'
import { AuthorizationService } from '../authorization.service'

export class EmployeeService {
    urls
    
  constructor() {
    this.urls = Util.getUrls()
  }
  getEmployees() : Promise<any> {
        const url: string = `${this.getUrlStart()}${this.urls.getEmployees}`
     
        return axios.get(url,{headers: AuthorizationService.getAuthorizationHeader()});
    }

   
    getUrlStart(): string {
        return this.urls.host + this.urls.apiRoot
      }
}

