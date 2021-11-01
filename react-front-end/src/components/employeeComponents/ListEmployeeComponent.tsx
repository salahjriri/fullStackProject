import * as React from 'react'
import {EmployeeService } from '../../services/employeeservice/employer.service';
import { LoginResponse } from '../../models/login-response.model'
import { Util } from '../../utils/Util'
import { Constants } from '../../constants/constants'

import { EmployeeData } from '../../models/employee.data.model';


 interface EmployeeStates {
    employeesDataState:EmployeeData[]
  }
  interface EmployeeProps{
    
    setEmployeeData: (employees: EmployeeData[]) => void
   
 }

 export class ListEmployeeComponent  extends React.Component<EmployeeProps,EmployeeStates>  {
    employerService
  
    constructor(props:EmployeeProps) {
       
       super(props)
       this.employerService = new EmployeeService();
       this.state ={ employeesDataState:  [{
        id: 0,

        firstName: '',

        lastName: '',

         emailId: ''
    }]}
    
        // this.addEmployee = this.addEmployee.bind(this);
        // this.editEmployee = this.editEmployee.bind(this);
        // this.deleteEmployee = this.deleteEmployee.bind(this);
    }

    // deleteEmployee(id){
    //     EmployeeService.deleteEmployee(id).then( res => {
    //         this.setState({employees: this.state.employees.filter(employee => employee.id !== id)});
    //     });
    // }
    // viewEmployee(id){
    //     this.props.history.push(`/view-employee/${id}`);
    // }
    // editEmployee(id){
    //     this.props.history.push(`/add-employee/${id}`);
    // }
  
    componentDidMount(): void {
        let loginResponse: LoginResponse = Util.getParsedDataFromLocalStorage(Constants.LOCAL_STORAGE_EMPLOYE_DATA)
        if (loginResponse) {
            this.employerService.getEmployees().then((res: { data: any; }) => {
          
            const employeeData : EmployeeData[] = res.data;
           
            if(employeeData){
                this.setState({ employeesDataState: employeeData});
            }
            
        });
    }
    }

    // addEmployee(){
    //     this.props.history.push('/add-employee/_adds');
    // }

    render(): React.ReactNode {
        const {employeesDataState} = this.state
        const isDataLoaded: boolean = (employeesDataState !=null)
      
        return (
            <div>
                 <h2 className="text-center">Employees List</h2>
                 <div className = "row">
                    {/* <button className="btn btn-primary" onClick={this.addEmployee}> Add Employee</button> */}
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Employee First Name</th>
                                    <th> Employee Last Name</th>
                                    <th> Employee Email Id</th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            {
                               
                            isDataLoaded &&
                            
                            <React.Fragment>
                            
                            { <tbody>
                                { 
                                    employeesDataState.map(
                                        employee => 
                                        <tr key = {employee.id}>
                                             <td> { employee.firstName} </td>   
                                             <td> {employee.lastName}</td>
                                             <td> {employee.emailId}</td>
                                             <td>
                                                 {/* <button onClick={ () => this.editEmployee(employee.id)} className="btn btn-info">Update </button> */}
                                                 {/* <button style={{marginLeft: "10px"}} onClick={ () => this.deleteEmployee(employee.id)} className="btn btn-danger">Delete </button> */}
                                                 {/* <button style={{marginLeft: "10px"}} onClick={ () => this.viewEmployee(employee.id)} className="btn btn-info">View </button>*/}
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody> }
                            </React.Fragment>
    }
                        </table>

                 </div>

            </div>
        )
    }
}

