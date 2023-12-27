import React, { Component } from 'react'
import DomainVersionService from '../services/DomainVersionService';

class UpdateDomainVersionComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
        }
        this.updateDomainVersion = this.updateDomainVersion.bind(this);

    }

    componentDidMount(){
        DomainVersionService.getDomainVersionById(this.state.id).then( (res) =>{
            let domainVersion = res.data;
            this.setState({
            });
        });
    }

    updateDomainVersion = (e) => {
        e.preventDefault();
        let domainVersion = {
            domainVersionId: this.state.id,
        };
        console.log('domainVersion => ' + JSON.stringify(domainVersion));
        console.log('id => ' + JSON.stringify(this.state.id));
        DomainVersionService.updateDomainVersion(domainVersion).then( res => {
            this.props.history.push('/domainVersions');
        });
    }


    cancel(){
        this.props.history.push('/domainVersions');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update DomainVersion</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateDomainVersion}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateDomainVersionComponent
