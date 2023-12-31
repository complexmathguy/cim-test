import React, { Component } from 'react'
import ExtensionVersionService from '../services/ExtensionVersionService';

class UpdateExtensionVersionComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
        }
        this.updateExtensionVersion = this.updateExtensionVersion.bind(this);

    }

    componentDidMount(){
        ExtensionVersionService.getExtensionVersionById(this.state.id).then( (res) =>{
            let extensionVersion = res.data;
            this.setState({
            });
        });
    }

    updateExtensionVersion = (e) => {
        e.preventDefault();
        let extensionVersion = {
            extensionVersionId: this.state.id,
        };
        console.log('extensionVersion => ' + JSON.stringify(extensionVersion));
        console.log('id => ' + JSON.stringify(this.state.id));
        ExtensionVersionService.updateExtensionVersion(extensionVersion).then( res => {
            this.props.history.push('/extensionVersions');
        });
    }


    cancel(){
        this.props.history.push('/extensionVersions');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update ExtensionVersion</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateExtensionVersion}>Save</button>
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

export default UpdateExtensionVersionComponent
