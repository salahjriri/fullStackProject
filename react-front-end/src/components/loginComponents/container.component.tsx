import React from 'react'
import { BrowserRouter as Router, Link, Route, Switch } from 'react-router-dom'
import { Roles } from '../../constants/roles.enum'
import { Routes } from '../../constants/routes.enum'
import { Role } from '../../models/role.model'
import { AuthenticationService } from '../../services/authentication.service'
import { UserService } from '../../services/user.service'

import '../../styles/container-component.style.css'
import { Util } from '../../utils/Util'


import { HomeComponent } from './home.component'
import { LoginComponent } from './login.component'
import { RegistrationComponent } from './registration.component'
import { UserComponent } from './user.component'
import { ListEmployeeComponent } from '../employeeComponents/ListEmployeeComponent'

import { EmployeeService } from '../../services/employeeservice/employer.service'

import { EmployeeData } from '../../models/employee.data.model'

export interface ContainerComponentProps {
}

export interface ContainerComponentStates {
  currentUsername: string,
  employeeData: EmployeeData[],
  showAdminButton: boolean
}

export class ContainerComponent
  extends React.Component <ContainerComponentProps, ContainerComponentStates> {

  authenticationService
  userService
  employeeService

  constructor(props: ContainerComponentProps) {
    super(props)
    this.authenticationService = new AuthenticationService()
    this.userService = new UserService()
    this.employeeService = new EmployeeService();
    this.state = {
      currentUsername: '',
      employeeData:  [{
        id: 0,

        firstName: '',

        lastName: '',

         emailId: ''
    }],
      showAdminButton: false
    }}
  

  componentDidMount(): void {
    
    this.refreshData()
  }

  logOut = () => {
    this.authenticationService.logout()
  }

  refreshData = () => {
    Util.refreshData(this.userService, this.setUsername, this.setRoles)
  }

  setUsername = (username: string) => {
    this.setState({currentUsername: username})
  }
  setEmployeeData = (employees :  EmployeeData[])=>{
    this.setState({employeeData:employees})
  }
  setRoles = (roles: Role[]) => {
    if (roles && roles.length > 0) {
      const roleNames: string[] = roles.map((role: Role) => role.name)
      const isCurrentUserAdmin: boolean =
        roleNames.includes(Roles.ROLE_ADMIN.toString())
      if (isCurrentUserAdmin) {
        this.setState({showAdminButton: true})
      }
    } else if (this.state.showAdminButton) {
      this.setState({showAdminButton: false})
    }
  }

  render() {
    
    const {
      currentUsername,
      showAdminButton
    } = this.state
    const isCurrentUserExists: boolean = currentUsername !== undefined && currentUsername !== ''

    return (
      <Router>
        <div>
          <nav className="menubar">
            <div className="menu-left">
              <Link
                to={Routes.ROOT}
                className="menu-item brand-link"

              >
                Acceuil
              </Link>
              <Link
                to={Routes.HOME}
                className="menu-item"
              >
                Home
              </Link>

              {
                showAdminButton &&

                <Link
                  to={Routes.ADMIN}
                  className="menu-item"
                >
                  Admin
                </Link>
              }

              {
                isCurrentUserExists &&

                <Link
                  to={Routes.USER}
                  className="menu-item"
                >
                  User
                </Link>
              }
               {
                isCurrentUserExists &&

                <Link
                  to={Routes.EMPLOYEE}
                  className="menu-item"
                >
                  Employees
                </Link>
              }
            </div>

            {
              isCurrentUserExists ?

                <div className="menu-right">
                  <Link
                    to={Routes.USER}
                    className="menu-item"
                  >
                    {currentUsername}
                  </Link>
                  <a
                    href={Routes.LOGIN}
                    className="menu-item"
                    onClick={this.logOut}
                  >
                    LogOut
                  </a>
                </div>

                :

                <div className="menu-right">
                  <Link
                    to={Routes.LOGIN}
                    className="menu-item"
                  >
                    Login
                  </Link>
                  <Link
                    to={Routes.REGISTER}
                    className="menu-item"
                  >
                    Sign Up
                  </Link>
                </div>
            }
          </nav>

          <Switch>
            <Route
              exact
              path={[Routes.ROOT, Routes.HOME]}
              component={HomeComponent}
            />
            <Route
              exact
              path={Routes.LOGIN}
              component={LoginComponent}
            />
            <Route
              exact
              path={Routes.REGISTER}
              component={RegistrationComponent}
            />
            <Route
              path={Routes.USER}
              render={routerProps => (
                <UserComponent
                  history={[]}
                  setUsername={this.setUsername}
                  setRoles={this.setRoles}
                />
              )}
            />
           <Route
              path={Routes.EMPLOYEE}
              render={routerProps => (
                <ListEmployeeComponent
                 
                 setEmployeeData ={this.setEmployeeData}
                  
                />
              )}
            />
          </Switch>
        </div>
      </Router>
    )
  }

}
