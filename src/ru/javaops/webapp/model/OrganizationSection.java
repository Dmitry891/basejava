package ru.javaops.webapp.model;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;

public class OrganizationSection extends Section {
    private final List<Organization> organization;

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organization) {
        Objects.requireNonNull(organization, "organizations must not be null");
        this.organization = organization;
    }

    public List<Organization> getOrganization() {
        return organization;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organization=" + organization +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organization.equals(that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization);
    }
}
